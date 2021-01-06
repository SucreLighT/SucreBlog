package cn.sucrelt.sucreblog.service.impl;

import cn.sucrelt.sucreblog.dao.CategoryMapper;
import cn.sucrelt.sucreblog.entity.Category;
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
    private CategoryMapper categoryMapper;


    @Override
    public PageResult getBlogCategoryPage(PageQueryUtil PageQueryUtil) {
        List<Category> categoryList = categoryMapper.getCategoryList(PageQueryUtil);
        int total = categoryMapper.getTotalCategories(PageQueryUtil);
        PageResult pageResult = new PageResult(categoryList, total, PageQueryUtil.getLimit(), PageQueryUtil.getPage());
        return pageResult;
    }

    @Override
    public int getTotalCategories() {
        return categoryMapper.getTotalCategories(null);
    }

    @Override
    public Boolean addCategory(String categoryName, String categoryIcon) {
        Category tempCategory = categoryMapper.selectByCategoryName(categoryName);
        if (tempCategory == null) {
            Category newCategory = new Category();
            newCategory.setCategoryName(categoryName);
            newCategory.setCategoryIcon(categoryIcon);
            return categoryMapper.insertSelective(newCategory) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon) {
        Category category = categoryMapper.selectByCategoryId(categoryId);
        if (category != null) {
            category.setCategoryIcon(categoryIcon);
            category.setCategoryName(categoryName);
            //更新blog实体中的分类属性
            // blogMapper.updateBlogCategorys(categoryName, blogCategory.getCategoryId(), new Integer[]{categoryId});
            return categoryMapper.updateByCategoryIdSelective(category) > 0;
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
        return categoryMapper.deleteCategories(ids) > 0;
    }
}
