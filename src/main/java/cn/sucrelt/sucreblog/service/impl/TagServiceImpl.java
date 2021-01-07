package cn.sucrelt.sucreblog.service.impl;

import cn.sucrelt.sucreblog.dao.BlogTagMapper;
import cn.sucrelt.sucreblog.dao.BlogTagRelationMapper;
import cn.sucrelt.sucreblog.entity.BlogTag;
import cn.sucrelt.sucreblog.service.TagService;
import cn.sucrelt.sucreblog.util.PageQueryUtil;
import cn.sucrelt.sucreblog.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: sucre
 * @date: 2021/01/07
 * @time: 15:48
 */

@Service
public class TagServiceImpl implements TagService {

    @Resource
    private BlogTagMapper blogTagMapper;

    @Resource
    private BlogTagRelationMapper blogTagRelationMapper;

    @Override
    public PageResult getBlogTagPage(PageQueryUtil pageQueryUtil) {
        List<BlogTag> tags = blogTagMapper.getTagList(pageQueryUtil);
        int total = blogTagMapper.getTotalTags(pageQueryUtil);
        PageResult pageResult = new PageResult(tags, total, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean addTag(String tagName) {
        BlogTag temp = blogTagMapper.selectByTagName(tagName);
        if (temp == null) {
            BlogTag blogTag = new BlogTag();
            blogTag.setTagName(tagName);
            return blogTagMapper.insertSelective(blogTag) > 0;
        }
        return false;
    }

    @Override
    public Boolean deleteTags(Integer[] ids) {
        //已存在关联关系不删除
        List<Long> relations = blogTagRelationMapper.selectDistinctTagIds(ids);
        if (!CollectionUtils.isEmpty(relations)) {
            return false;
        }
        //删除tag
        return blogTagMapper.deleteTags(ids) > 0;
    }
}
