package cn.sucrelt.sucreblog.dao;

import cn.sucrelt.sucreblog.entity.BlogTag;
import cn.sucrelt.sucreblog.util.PageQueryUtil;

import java.util.List;

/**
 * @description:
 * @author: sucre
 * @date: 2021/01/07
 * @time: 15:23
 */
public interface BlogTagMapper {
    List<BlogTag> getTagList(PageQueryUtil pageQueryUtil);

    int getTotalTags(PageQueryUtil pageQueryUtil);

    BlogTag selectByTagName(String tagName);

    int insertSelective(BlogTag blogTag);

    int deleteTags(Integer[] ids);
}
