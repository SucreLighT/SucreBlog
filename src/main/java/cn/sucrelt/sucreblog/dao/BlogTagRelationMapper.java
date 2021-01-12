package cn.sucrelt.sucreblog.dao;

import cn.sucrelt.sucreblog.entity.BlogTagRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @description: 博客和标签中间表对应的dao层接口
 * @author: sucre
 * @date: 2021/01/07
 * @time: 16:19
 */
public interface BlogTagRelationMapper {
    /**
     * 根据标签查询中间表中是否存在与之关联的数据
     * 如果存在说明该标签尚被使用，无法执行删除的操作（不考虑级联删除博客）
     *
     * @param tagIds
     * @return
     */
    List<Long> selectDistinctTagIds(Integer[] tagIds);

    /**
     * 批量插入博客和多个标签的关系数据
     *
     * @param blogTagRelations
     * @return
     */
    int insertRelations(@Param("relationList") List<BlogTagRelation> blogTagRelations);
}
