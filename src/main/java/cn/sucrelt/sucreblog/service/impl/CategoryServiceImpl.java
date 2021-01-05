package cn.sucrelt.sucreblog.service.impl;

import cn.sucrelt.sucreblog.dao.CategoryMapper;
import cn.sucrelt.sucreblog.entity.Category;
import cn.sucrelt.sucreblog.service.CategoryService;
import cn.sucrelt.sucreblog.util.PageQueryUtil;
import cn.sucrelt.sucreblog.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description:
 * @author: sucre
 * @date: 2020/12/31
 * @time: 14:16
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public PageResult getCategoryPage(PageQueryUtil pageQueryUtil) {
        List<Category> categoryList = categoryMapper.getCategoryList(pageQueryUtil);
        int total = categoryMapper.getTotalCategories(pageQueryUtil);

        PageResult pageResult = new PageResult(categoryList, total, pageQueryUtil.getLimit(), pageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public boolean addCategory(String categoryName, String categoryIcon) {
        Category category = categoryMapper.selectByCategoryName(categoryName);
        if (category == null) {
            Category newCategory = new Category();
            newCategory.setCategoryName(categoryName);
            newCategory.setCategoryIcon(categoryIcon);
            return categoryMapper.insertSelective(newCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        Category category = categoryMapper.selectByCategoryId(categoryId);
        if (category != null) {
            category.setCategoryName(categoryName);
            category.setCategoryIcon(categoryIcon);

            //博客的分类属性同步修改
            // blogMapper.updateBlogCategorys(categoryName, blogCategory.getCategoryId(), new Integer[]{categoryId});
            return categoryMapper.updateByCategoryIdSelective(category) > 0;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteCategories(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //修改tb_blog表
        // blogMapper.updateBlogCategorys("默认分类", 0, ids);
        //删除分类数据
        return categoryMapper.deleteCategories(ids) > 0;
    }
}
