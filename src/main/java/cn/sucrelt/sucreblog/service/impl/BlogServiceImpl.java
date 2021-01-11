package cn.sucrelt.sucreblog.service.impl;

import cn.sucrelt.sucreblog.dao.BlogMapper;
import cn.sucrelt.sucreblog.dao.BlogTagMapper;
import cn.sucrelt.sucreblog.dao.BlogTagRelationMapper;
import cn.sucrelt.sucreblog.dao.CategoryMapper;
import cn.sucrelt.sucreblog.entity.Blog;
import cn.sucrelt.sucreblog.entity.BlogCategory;
import cn.sucrelt.sucreblog.entity.BlogTag;
import cn.sucrelt.sucreblog.entity.BlogTagRelation;
import cn.sucrelt.sucreblog.service.BlogService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: sucre
 * @date: 2021/01/11
 * @time: 16:41
 */
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private BlogTagMapper blogTagMapper;

    @Resource
    private BlogTagRelationMapper blogTagRelationMapper;

    @Override
    @Transactional
    public String saveBlog(Blog blog) {
        // 设置博客分类
        BlogCategory blogCategory = categoryMapper.selectByCategoryId(blog.getBlogCategoryId());
        if (blogCategory == null) {
            blog.setBlogCategoryId(0);
            blog.setBlogCategoryName("默认分类");
        } else {
            //设置博客分类名称
            blog.setBlogCategoryName(blogCategory.getCategoryName());
            //分类的排序值加1
            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
        }
        // 设置博客的标签
        String[] tags = blog.getBlogTags().split(",");
        if (tags.length > 6) {
            return "标签数过多，应不超过6个！";
        }
        // 保存文章
        if (blogMapper.insertSelective(blog) > 0) {
            // 新增的tag对象
            List<BlogTag> tagListForInsert = new ArrayList<>();
            // 所有的tag对象，用于建立关系数据
            List<BlogTag> allTagsList = new ArrayList<>();

            // 判断当前文章的标签是都在tag表中已存在
            for (int i = 0; i < tags.length; i++) {
                BlogTag tag = blogTagMapper.selectByTagName(tags[i]);
                //不存在就新增
                if (tag == null) {
                    BlogTag tempTag = new BlogTag();
                    tempTag.setTagName(tags[i]);
                    tagListForInsert.add(tempTag);
                } else {
                    allTagsList.add(tag);
                }
            }
            // 将新增标签数据插入到标签表中
            if (!CollectionUtils.isEmpty(tagListForInsert)) {
                blogTagMapper.insertBlogTags(tagListForInsert);
            }
            // 更新博客所属分类，修改分类排序值，上述已将排序值加一
            categoryMapper.updateByCategoryIdSelective(blogCategory);

            // 将新增博客和标签的关系数据插入到中间表中
            List<BlogTagRelation> blogTagRelations = new ArrayList<>();
            allTagsList.addAll(tagListForInsert);
            for (BlogTag tag : allTagsList) {
                BlogTagRelation blogTagRelation = new BlogTagRelation();
                blogTagRelation.setBlogId(blog.getBlogId());
                blogTagRelation.setTagId(tag.getTagId());
                blogTagRelations.add(blogTagRelation);
            }
            if (blogTagRelationMapper.insertRelations(blogTagRelations) > 0) {
                return "success";
            }
        }
        return "保存失败";
    }
}
