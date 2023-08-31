package com.yuxuan66.ecmc.job.modules;

import com.yuxuan66.ecmc.cache.EveCache;
import com.yuxuan66.ecmc.cache.entity.*;
import com.yuxuan66.ecmc.cache.mapper.*;
import com.yuxuan66.ecmc.job.utils.SdeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Sir丶雨轩
 * @since 2023/1/13
 */
@Component
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SdeJob  {
    @Resource
    private TypeMapper typeMapper;
    @Resource
    private GroupMapper groupMapper;
    @Resource
    private MetaGroupMapper metaGroupMapper;
    @Resource
    private MarketGroupMapper marketGroupMapper;
    @Resource
    private BluePrintMapper bluePrintMapper;
    @Resource
    private BluePrintSkillMapper bluePrintSkillMapper;
    @Resource
    private BluePrintProductsMapper bluePrintProductsMapper;
    @Resource
    private BluePrintMaterialsMapper bluePrintMaterialsMapper;
    @Resource
    private CategoryMapper categoryMapper;
    private final EveCache eveCache;


    public void process() throws Exception {
        // 开始读取各个文件清洗数据库并清洗缓存
        // 第一步 语言包
        String basePath = "";

        // 第二步 分类
        List<Category> categoryList = SdeUtil.getCategoryList(basePath);
        categoryMapper.delete(null);
        categoryMapper.batchInsert(categoryList);
        // 第三步 分组
        List<Group> groupList = SdeUtil.getGroupList(basePath);
        // 需要获取从分类中获取名字
        for (Group group : groupList) {
            Optional<Category> category = categoryList.stream().filter(item -> item.getId().equals(group.getCategoryId())).findFirst();
            if (category.isPresent()) {
                group.setCategoryName(category.get().getName());
                group.setCategoryNameEn(category.get().getNameEn());
            }
        }
        groupMapper.delete(null);
        groupMapper.batchInsert(groupList);

        List<MetaGroup> metaGroupList = SdeUtil.getMetaGroupList(basePath);

        List<MarketGroup> marketGroupList = SdeUtil.getMarketGroupList(basePath);
        metaGroupMapper.delete(null);
        metaGroupMapper.batchInsert(metaGroupList);

        marketGroupMapper.delete(null);
        marketGroupMapper.batchInsert(marketGroupList);
        List<Type> typeList = SdeUtil.getTypeList(basePath);

        // 获取分组名称，元分组名称，市场分组名称
        for (Type type : typeList) {
            if (type.getGroupId() != null) {
                Optional<Group> group = groupList.stream().filter(item -> item.getId().equals(type.getGroupId())).findFirst();
                if (group.isPresent()) {
                    type.setGroupName(group.get().getName());
                    type.setGroupNameEn(group.get().getNameEn());
                    type.setCategoryId(group.get().getCategoryId());
                    type.setCategoryName(group.get().getCategoryName());
                    type.setCategoryNameEn(group.get().getCategoryNameEn());
                }
            }
            if (type.getMetaGroupId() != null) {
                Optional<MetaGroup> group = metaGroupList.stream().filter(item -> item.getId().equals(type.getMetaGroupId())).findFirst();
                if (group.isPresent()) {
                    type.setMetaGroupName(group.get().getName());
                    type.setMetaGroupNameEn(group.get().getNameEn());
                }
            }
            if (type.getMarketGroupId() != null) {
                Optional<MarketGroup> group = marketGroupList.stream().filter(item -> item.getId().equals(type.getMarketGroupId())).findFirst();
                if (group.isPresent()) {
                    type.setMarketGroupName(group.get().getName());
                    type.setMarketGroupNameEn(group.get().getNameEn());
                }
            }
        }


        typeMapper.delete(null);
        typeMapper.batchInsert(typeList);

        List<BluePrint> bluePrintList = SdeUtil.getBluePrint(basePath, eveCache.getTypeMap());

        List<BluePrintProducts> bluePrintProductsList = new ArrayList<>();
        List<BluePrintMaterials> bluePrintMaterialsList = new ArrayList<>();
        List<BluePrintSkill> bluePrintSkillList = new ArrayList<>();
        for (BluePrint bluePrint : bluePrintList) {
            if (bluePrint.getPrintProductsList() != null) {
                bluePrintProductsList.addAll(bluePrint.getPrintProductsList());
            }

            if (bluePrint.getPrintMaterialsList() != null) {
                bluePrintMaterialsList.addAll(bluePrint.getPrintMaterialsList());
            }

            if (bluePrint.getPrintSkillList() != null) {
                bluePrintSkillList.addAll(bluePrint.getPrintSkillList());
            }
        }

        bluePrintMapper.delete(null);
        bluePrintProductsMapper.delete(null);
        bluePrintMaterialsMapper.delete(null);
        bluePrintSkillMapper.delete(null);

        bluePrintMapper.batchInsert(bluePrintList);

        bluePrintProductsMapper.batchInsert(bluePrintProductsList);
        bluePrintMaterialsMapper.batchInsert(bluePrintMaterialsList);
        bluePrintSkillMapper.batchInsert(bluePrintSkillList);
    }
}
