package cn.sucrelt.sucreblog.dao;

import cn.sucrelt.sucreblog.entity.BlogTag;
import cn.sucrelt.sucreblog.util.PageQueryUtil;

import java.util.List;

/**
 * @description: 标签模块的dao接口
 * @author: sucre
 * @date: 2021/01/07
 * @time: 15:23
 */
public interface BlogTagMapper {
    /**
     * 获取标签的分页数据
     *
     * @param pageQueryUtil
     * @return
     */
    List<BlogTag> getTagList(PageQueryUtil pageQueryUtil);

    /**
     * 获取所有的标签数
     *
     * @param pageQueryUtil
     * @return
     */
    int getTotalTags(PageQueryUtil pageQueryUtil);

    /**
     * 根据标签名查找数据库中的标签数据
     *
     * @param tagName
     * @return
     */
    BlogTag selectByTagName(String tagName);

    /**
     * 添加插入标签数据，插入前判断各字段不为空
     *
     * @param blogTag
     * @return
     */
    int insertSelective(BlogTag blogTag);

    /**
     * 根据标签id批量删除标签
     *
     * @param ids
     * @return
     */
    int deleteTags(Integer[] ids);

    /**
     * 添加博客时，插入该博客的所有新增标签
     *
     * @param tagListForInsert
     */
    int insertBlogTags(List<BlogTag> tagListForInsert);
}
