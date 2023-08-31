package com.yuxuan66.ecmc.common.utils.tree;

import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 处理树的工具
 * @author Sir丶雨轩
 * @since 2022/9/16
 */
public final class TreeUtil<T> {

    private List<T> treeList;

    private final List<Map<String,Object>> resultList = Collections.synchronizedList(new ArrayList<>());

    @SuppressWarnings("unchecked")
    public List<T> menuList(List<T> list) {

        if (list.isEmpty()) {
            return list;
        }

        Object temp = list.get(0);
        treeList = list;

        Field parentField = getParentField(temp);

        Field idField = getIdField(temp);

        for (Object object : list) {
            Map<String, Object> mapArr = new LinkedHashMap<>();
            Object pid = getFieldVal(parentField, object);
            Object id = getFieldVal(idField, object);
            // 如果没有Pid,或者Pid等于默认值则表示这条记录为顶级
            if (pid == null || pid.equals(parentField.getAnnotation(TreeId.class).defaultId()) || treeList.stream().noneMatch(item -> pid.equals(getFieldVal(idField, item)))) {
                mapArr.putAll(getFields(object, false));
                mapArr.putAll(getSecondLevelFields(object));
                List<?> childrenList = menuChild(id);
                mapArr.put("hasChildren", !childrenList.isEmpty());
                mapArr.put("alwaysShow",childrenList.isEmpty() ? 0 : 1);
                if (!childrenList.isEmpty()) {
                    mapArr.put("children", childrenList);
                }
                resultList.add(mapArr);
            }
        }

        return (List<T>) resultList;
    }


    private List<?> menuChild(Object childId) {

        List<Object> list = new ArrayList<>();
        Object temp = treeList.get(0);

        Field parentField = getParentField(temp);

        Field idField = getIdField(temp);
        for (Object object : treeList) {
            Map<String, Object> mapArr = new LinkedHashMap<>();
            Object pid = getFieldVal(parentField, object);
            Object id = getFieldVal(idField, object);
            if (pid != null && pid.equals(childId)) {
                mapArr.putAll(getFields(object, true));
                mapArr.putAll(getSecondLevelFields(object));
                List<?> childrenList = menuChild(id);
                mapArr.put("hasChildren", childrenList.isEmpty() ? 0 : 1);
                mapArr.put("alwaysShow", childrenList.isEmpty() ? 0 : 1);
                if (!childrenList.isEmpty()) {
                    mapArr.put("children", childrenList);
                }
                list.add(mapArr);
            }
        }
        return list;
    }


    @SneakyThrows
    private Map<String, Map<String, Object>> getSecondLevelFields(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();

        for (Field field : fields) {
            if (field.isAnnotationPresent(TreeField.class)) {
                TreeField treeField = field.getAnnotation(TreeField.class);
                if (StrUtil.isNotBlank(treeField.secondLevel())) {

                    Map<String, Object> secondLevel = result.get(treeField.secondLevel());
                    if (secondLevel == null) {
                        secondLevel = new LinkedHashMap<>();
                    }
                    String key = treeField.secondLevelName();
                    if (StrUtil.isBlank(key)) {
                        key = field.getName();
                    }
                    if(StrUtil.isNotBlank(treeField.like())){
                        field = object.getClass().getDeclaredField(treeField.like());
                    }
                    Object val = getFieldVal(field, object);
                    secondLevel.put(key, val);
                    result.put(treeField.secondLevel(), secondLevel);
                }
            }
        }
        return result;
    }

    @SneakyThrows
    private Map<String, Object> getFields(Object object, boolean onlyFirst) {
        Field[] fields = object.getClass().getDeclaredFields();
        Map<String, Object> result = new LinkedHashMap<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(TreeField.class)) {
                TreeField treeField = field.getAnnotation(TreeField.class);
                if(StrUtil.isNotBlank(treeField.like())){
                    result.put(treeField.value(), getFieldVal(object.getClass().getDeclaredField(treeField.like()), object));
                    continue;
                }
                if (onlyFirst && treeField.onlyFirst()) {
                    continue;
                }
                String key = treeField.value();
                if (StrUtil.isBlank(key)) {
                    key = field.getName();
                }
                Object val = getFieldVal(field, object);
                if(key.contains(",")){
                    Arrays.asList(key.split(",")).forEach(item->{
                        result.put(item, val);
                    });

                }else{
                    result.put(key, val);
                }

            }
        }

        return result;
    }

    /**
     * 获取字段的值
     *
     * @param field  字段
     * @param object 对象
     * @return 值
     */
    private Object getFieldVal(Field field, Object object) {
        field.setAccessible(true);
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(false);
        }
        return null;
    }

    /**
     * 获取表示父id的字段
     *
     * @param object 对象
     * @return 字段
     */
    private Field getParentField(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            TreeId treeId = field.getAnnotation(TreeId.class);
            if (treeId != null && treeId.isParent()) {
                return field;
            }
        }
        throw new RuntimeException("没有找到标有@Parent的字段");
    }

    /**
     * 获取表示id的字段
     *
     * @param object 对象
     * @return 字段
     */
    private Field getIdField(Object object) {
        for (Field field : object.getClass().getDeclaredFields()) {
            TreeId treeId = field.getAnnotation(TreeId.class);
            if (treeId != null && !treeId.isParent()) {
                return field;
            }
        }
        throw new RuntimeException("没有找到标有@Id的字段");
    }

}
