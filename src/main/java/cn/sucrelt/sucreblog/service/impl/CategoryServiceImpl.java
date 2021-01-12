package cn.sucrelt.sucreblog.service.impl;

import cn.sucrelt.sucreblog.dao.BlogCategoryMapper;
import cn.sucrelt.sucreblog.entity.BlogCategory;
import cn.sucrelt.sucreblog.service.CategoryService;
import cn.sucrelt.sucreblog.util.PageQueryUtil;
import cn.sucrelt.sucreblog.util.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: sucre
 * @date: 2020/12/31
 * @time: 14:16
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private BlogCategoryMapper blogCategoryMapper;


    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil PageQueryUtil) {
        List<BlogCategory> blogCategoryList = blogCategoryMapper.getCategoryList(PageQueryUtil);
        int total = blogCategoryMapper.getTotalCategories(PageQueryUtil);
        PageResult pageResult = new PageResult(blogCategoryList, total, PageQueryUtil.getLimit(), PageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalCategories() {
        return blogCategoryMapper.getTotalCategories(null);
    }

    @Override
    public Boolean addCategory(String categoryName, String categoryIcon) {
        BlogCategory tempBlogCategory = blogCategoryMapper.selectByCategoryName(categoryName);
        if (tempBlogCategory == null) {
            BlogCategory newBlogCategory = new BlogCategory();
            newBlogCategory.setCategoryName(categoryName);
            newBlogCategory.setCategoryIcon(categoryIcon);
            return blogCategoryMapper.insertSelective(newBlogCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        BlogCategory blogCategory = blogCategoryMapper.selectByCategoryId(categoryId);
        if (blogCategory != null) {
            blogCategory.setCategoryIcon(categoryIcon);
            blogCategory.setCategoryName(categoryName);
            //更新blog实体中的分类属性
            // blogMapper.updateBlogCategorys(categoryName, blogCategory.getCategoryId(), new Integer[]{categoryId});
            return blogCategoryMapper.updateByCategoryIdSelective(blogCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean deleteCategories(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //更新blog实体中的分类属性
        // blogMapper.updateBlogCategorys("默认分类", 0, ids);
        return blogCategoryMapper.deleteCategories(ids) > 0;
    }

    @Override
    public List<BlogCategory> getAllCategories() {
        return blogCategoryMapper.getCategoryList(null);
    }
}
