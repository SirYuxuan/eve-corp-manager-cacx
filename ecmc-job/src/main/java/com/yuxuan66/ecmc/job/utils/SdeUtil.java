package com.yuxuan66.ecmc.job.utils;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.yaml.YamlUtil;
import com.yuxuan66.ecmc.cache.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Sir丶雨轩
 * @since 2021/12/15
 */
public class SdeUtil {

    /**
     * 从sde文件中获取type list
     *
     * @param basePath sde目录
     * @return type list
     */
    @SuppressWarnings("unchecked")
    public static List<Type> getTypeList(String basePath) {
        Map<Integer, Object> typeId = YamlUtil.loadByPath(basePath + SdeConst.TYPE_ID, Map.class);
        List<Type> typeList = new ArrayList<>();
        for (Map.Entry<Integer, Object> item : typeId.entrySet()) {
            Type type = new Type();
            type.setId(item.getKey());
            if (item.getValue() instanceof Map) {
                Map<String, Object> data = (Map<String, Object>) item.getValue();
                type.setGroupId(Convert.toInt(data.get("groupID"), null));
                type.setMetaGroupId(Convert.toInt(data.get("metaGroupID"), null));
                type.setMarketGroupId(Convert.toInt(data.get("marketGroupID"), null));
                if (data.containsKey("name") && data.get("name") instanceof Map) {
                    Map<String, String> nameMap = (Map<String, String>) data.get("name");
                    type.setName(nameMap.get("zh"));
                    type.setNameEn(nameMap.get("en"));
                    if (StrUtil.isEmpty(type.getName())) {
                        type.setName(type.getNameEn());
                    }
                }
                if (data.containsKey("description") && data.get("description") instanceof Map) {
                    Map<String, String> descriptionMap = (Map<String, String>) data.get("description");
                    type.setDescription(descriptionMap.get("zh"));
                    type.setDescriptionEn(descriptionMap.get("en"));
                    if (StrUtil.isEmpty(type.getDescription())) {
                        type.setDescription(type.getDescriptionEn());
                    }
                }
                typeList.add(type);
            }
        }
        return typeList;
    }

    /**
     * 从sde文件中获取category list
     *
     * @param basePath sde目录
     * @return category list
     */
    @SuppressWarnings("unchecked")
    public static List<Category> getCategoryList(String basePath) {
        Map<Integer, Object> categoryId = YamlUtil.loadByPath(basePath + SdeConst.CATEGORY_ID, Map.class);
        List<Category> categoryList = new ArrayList<>();
        for (Map.Entry<Integer, Object> item : categoryId.entrySet()) {
            Category category = new Category();
            category.setId(item.getKey());
            if (item.getValue() instanceof Map) {
                Map<String, Object> data = (Map<String, Object>) item.getValue();
                if (data.containsKey("name") && data.get("name") instanceof Map) {
                    Map<String, String> nameMap = (Map<String, String>) data.get("name");
                    category.setName(nameMap.get("zh"));
                    category.setNameEn(nameMap.get("en"));
                    if (StrUtil.isEmpty(category.getName())) {
                        category.setName(category.getNameEn());
                    }
                }

                categoryList.add(category);
            }
        }
        return categoryList;
    }

    /**
     * 从sde文件中获取group list
     *
     * @param basePath sde目录
     * @return group list
     */
    @SuppressWarnings("unchecked")
    public static List<Group> getGroupList(String basePath) {
        Map<Integer, Object> groupId = YamlUtil.loadByPath(basePath + SdeConst.GROUP_ID, Map.class);
        List<Group> groupList = new ArrayList<>();
        for (Map.Entry<Integer, Object> item : groupId.entrySet()) {
            Group group = new Group();
            group.setId(item.getKey());
            if (item.getValue() instanceof Map) {
                Map<String, Object> data = (Map<String, Object>) item.getValue();
                group.setCategoryId(Convert.toInt(data.get("categoryID"), 0));
                group.setIconId(Convert.toInt(data.get("iconID"), 0));
                if (data.containsKey("name") && data.get("name") instanceof Map) {
                    Map<String, String> nameMap = (Map<String, String>) data.get("name");
                    group.setName(nameMap.get("zh"));
                    group.setNameEn(nameMap.get("en"));
                    if (StrUtil.isEmpty(group.getName())) {
                        group.setName(group.getNameEn());
                    }
                }

                groupList.add(group);
            }
        }
        return groupList;
    }

    /**
     * 从sde文件中获取 meta group list
     *
     * @param basePath sde目录
     * @return group list
     */
    @SuppressWarnings("unchecked")
    public static List<MetaGroup> getMetaGroupList(String basePath) {
        Map<Integer, Object> groupId = YamlUtil.loadByPath(basePath + SdeConst.META_GROUP_ID, Map.class);
        List<MetaGroup> groupList = new ArrayList<>();
        for (Map.Entry<Integer, Object> item : groupId.entrySet()) {
            MetaGroup group = new MetaGroup();
            group.setId(item.getKey());
            if (item.getValue() instanceof Map) {
                Map<String, Object> data = (Map<String, Object>) item.getValue();
                if (data.containsKey("nameID") && data.get("nameID") instanceof Map) {
                    Map<String, String> nameMap = (Map<String, String>) data.get("nameID");
                    group.setName(nameMap.get("zh"));
                    group.setNameEn(nameMap.get("en"));
                    if (StrUtil.isEmpty(group.getName())) {
                        group.setName(group.getNameEn());
                    }
                }

                groupList.add(group);
            }
        }
        return groupList;
    }

    /**
     * 从sde文件中获取 market group list
     *
     * @param basePath sde目录
     * @return group list
     */
    @SuppressWarnings("unchecked")
    public static List<MarketGroup> getMarketGroupList(String basePath) {
        Map<Integer, Object> groupId = YamlUtil.loadByPath(basePath + SdeConst.MARKET_GROUP_ID, Map.class);
        List<MarketGroup> groupList = new ArrayList<>();
        for (Map.Entry<Integer, Object> item : groupId.entrySet()) {
            MarketGroup group = new MarketGroup();
            group.setId(item.getKey());
            if (item.getValue() instanceof Map) {
                Map<String, Object> data = (Map<String, Object>) item.getValue();
                group.setPid(data.containsKey("parentGroupID") ? Convert.toInt(data.get("parentGroupID")) : null);
                group.setIconId(Convert.toInt(data.get("iconID")));
                group.setHasType(Convert.toBool(data.get("hasTypes")));
                if (data.containsKey("nameID") && data.get("nameID") instanceof Map) {
                    Map<String, String> nameMap = (Map<String, String>) data.get("nameID");
                    group.setName(nameMap.get("zh"));
                    group.setNameEn(nameMap.get("en"));
                    if (StrUtil.isEmpty(group.getName())) {
                        group.setName(group.getNameEn());
                    }
                }

                if (data.containsKey("descriptionID") && data.get("descriptionID") instanceof Map) {
                    Map<String, String> descriptionMap = (Map<String, String>) data.get("descriptionID");
                    group.setDescription(descriptionMap.get("zh"));
                    group.setDescriptionEn(descriptionMap.get("en"));
                    if (StrUtil.isEmpty(group.getDescription())) {
                        group.setDescription(group.getDescriptionEn());
                    }
                }

                groupList.add(group);
            }
        }
        return groupList;
    }

    @SuppressWarnings("unchecked")
    public static void materialSkillProduceHandling(BluePrint bluePrint, Map<String, Object> data, int type, Map<Integer,Type> typeMap){
        // 材料
        if (data.containsKey("materials")) {
            List<Map<String, Object>> materials = (List<Map<String, Object>>) data.get("materials");
            List<BluePrintMaterials> printMaterials = new ArrayList<>();
            for (Map<String, Object> material : materials) {
                BluePrintMaterials bluePrintMaterials = new BluePrintMaterials();
                bluePrintMaterials.setBluePrintId(bluePrint.getId());
                bluePrintMaterials.setType(type);
                bluePrintMaterials.setTypeId(Convert.toInt(material.get("typeID")));
                Type eveType = typeMap.get(bluePrintMaterials.getTypeId());
                if(eveType!=null){
                    bluePrintMaterials.setTypeName(eveType.getName());
                    bluePrintMaterials.setTypeNameEn(eveType.getNameEn());
                }
                bluePrintMaterials.setQuantity(Convert.toInt(material.get("quantity")));
                printMaterials.add(bluePrintMaterials);
            }
            bluePrint.setPrintMaterialsList(printMaterials);
        }
        // 技能
        if (data.containsKey("skills")) {
            List<Map<String, Object>> skills = (List<Map<String, Object>>) data.get("skills");
            List<BluePrintSkill> printSkills = new ArrayList<>();
            for (Map<String, Object> skill : skills) {
                BluePrintSkill bluePrintSkill = new BluePrintSkill();
                bluePrintSkill.setBluePrintId(bluePrint.getId());
                bluePrintSkill.setType(type);
                bluePrintSkill.setSkillsId(Convert.toInt(skill.get("typeID")));
                Type eveType = typeMap.get(bluePrintSkill.getSkillsId());
                if(eveType!=null){
                    bluePrintSkill.setSkillsName(eveType.getName());
                    bluePrintSkill.setSkillsNameEn(eveType.getNameEn());
                }
                bluePrintSkill.setLevel(Convert.toInt(skill.get("level")));
                printSkills.add(bluePrintSkill);
            }
            bluePrint.setPrintSkillList(printSkills);
        }

        // 产出
        if (data.containsKey("products")) {
            List<Map<String, Object>> products = (List<Map<String, Object>>) data.get("products");
            List<BluePrintProducts> printProducts = new ArrayList<>();
            for (Map<String, Object> product : products) {
                BluePrintProducts bluePrintProduct = new BluePrintProducts();
                bluePrintProduct.setBluePrintId(bluePrint.getId());
                bluePrintProduct.setType(type);
                bluePrintProduct.setProductsId(Convert.toInt(product.get("typeID")));
                Type eveType = typeMap.get(bluePrintProduct.getProductsId());
                if(eveType!=null){
                    bluePrintProduct.setProductsName(eveType.getName());
                    bluePrintProduct.setProductsNameEn(eveType.getNameEn());
                }
                bluePrintProduct.setProductsQuantity(Convert.toInt(product.get("quantity")));
                bluePrintProduct.setProbability(Convert.toDouble(product.get("probability")));
                printProducts.add(bluePrintProduct);
            }
            bluePrint.setPrintProductsList(printProducts);
        }

    }


    /**
     * 从sde文件中获取 蓝图 list
     *
     * @param basePath sde目录
     * @return 蓝图 list
     */
    @SuppressWarnings("unchecked")
    public static List<BluePrint> getBluePrint(String basePath,Map<Integer,Type> typeMap) {
        Map<Integer, Object> groupId = YamlUtil.loadByPath(basePath + SdeConst.BLUE_PRINT, Map.class);
        List<BluePrint> printList = new ArrayList<>();
        for (Map.Entry<Integer, Object> item : groupId.entrySet()) {
            if (item.getValue() instanceof Map) {
                Map<String, Object> data = (Map<String, Object>) item.getValue();
                BluePrint bluePrint = new BluePrint();
                bluePrint.setId(item.getKey());
                Type type = typeMap.get(item.getKey());
                if(type != null){
                    bluePrint.setName(type.getName());
                    bluePrint.setNameEn(type.getNameEn());
                    bluePrint.setGroupId(type.getGroupId());
                    bluePrint.setGroupName(type.getGroupName());
                    bluePrint.setGroupNameEn(type.getGroupNameEn());

                    bluePrint.setMetaGroupId(type.getMetaGroupId());
                    bluePrint.setMarketGroupName(type.getMarketGroupName());
                    bluePrint.setMarketGroupNameEn(type.getMarketGroupNameEn());

                    bluePrint.setMarketGroupId(type.getMarketGroupId());
                    bluePrint.setMarketGroupName(type.getMarketGroupName());
                    bluePrint.setMarketGroupNameEn(type.getMarketGroupNameEn());

                    bluePrint.setCategoryId(type.getCategoryId());
                    bluePrint.setCategoryName(type.getCategoryName());
                    bluePrint.setCategoryNameEn(type.getCategoryNameEn());
                }
                bluePrint.setMaxLimit(Convert.toInt(data.get("maxProductionLimit")));

                Map<String, Object> activities = (Map<String, Object>) data.get("activities");
                // 复制
                if (activities.containsKey("copying")) {
                    Map<String, Object> copying = (Map<String, Object>) activities.get("copying");
                    bluePrint.setCopyTime(Convert.toInt(copying.get("time")));
                    materialSkillProduceHandling(bluePrint,copying,BluePrintConst.COPY,typeMap);
                }

                // 材料优化
                if (activities.containsKey("research_material")) {
                    Map<String, Object> researchMaterial = (Map<String, Object>) activities.get("research_material");
                    bluePrint.setResearchMaterialTime(Convert.toInt(researchMaterial.get("time")));
                    materialSkillProduceHandling(bluePrint,researchMaterial,BluePrintConst.MATERIAL_OPTIMIZATION,typeMap);
                }

                // 时间优化
                if (activities.containsKey("research_time")) {
                    Map<String, Object> researchTime = (Map<String, Object>) activities.get("research_time");
                    bluePrint.setResearchTimeTime(Convert.toInt(researchTime.get("time")));
                    materialSkillProduceHandling(bluePrint,researchTime,BluePrintConst.TIME_OPTIMIZATION,typeMap);
                }

                // 发明
                if (activities.containsKey("invention")) {
                    Map<String, Object> invention = (Map<String, Object>) activities.get("invention");
                    bluePrint.setInventionTime(Convert.toInt(invention.get("time")));
                    materialSkillProduceHandling(bluePrint,invention,BluePrintConst.INVENTION,typeMap);
                }

                // 制造
                if (activities.containsKey("manufacturing")) {
                    Map<String, Object> manufacturing = (Map<String, Object>) activities.get("manufacturing");
                    bluePrint.setManufacturingTime(Convert.toInt(manufacturing.get("time")));
                    materialSkillProduceHandling(bluePrint,manufacturing,BluePrintConst.MANUFACTURE,typeMap);
                }

                // 反应
                if (activities.containsKey("reaction")) {
                    Map<String, Object> reaction = (Map<String, Object>) activities.get("reaction");
                    bluePrint.setReactionTime(Convert.toInt(reaction.get("time")));
                    materialSkillProduceHandling(bluePrint,reaction,BluePrintConst.REACTION,typeMap);
                }

                printList.add(bluePrint);


            }
        }

        return printList;
    }
}
