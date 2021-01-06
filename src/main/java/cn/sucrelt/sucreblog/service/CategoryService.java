package cn.sucrelt.sucreblog.service;

import cn.sucrelt.sucreblog.util.PageQueryUtil;
import cn.sucrelt.sucreblog.util.PageResult;

/**
 * @description:
 * @author: sucre
 * @date: 2020/12/31
 * @time: 14:15
 */
public interface CategoryService {

    /**
     * 获取分类的分页数据
     *
     * @param PageQueryUtil
     * @return
     */
    PageResult getBlogCategoryPage(PageQueryUtil PageQueryUtil);

    /**
     * 获取总的分类数
     *
     * @return
     */
    int getTotalCategories();

    /**
     * 添加分类
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean addCategory(String categoryName, String categoryIcon);

    /**
     * 修改分类
     *
     * @param categoryId
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    Boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    /**
     * 批量删除分类
     *
     * @param ids
     * @return
     */
    Boolean deleteCategories(Integer[] ids);
}
