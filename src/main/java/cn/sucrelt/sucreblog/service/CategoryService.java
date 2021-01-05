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
     * 查询分类的分页数据
     *
     * @param pageQueryUtil
     * @return
     */
    PageResult getCategoryPage(PageQueryUtil pageQueryUtil);


    /**
     * 添加分类数据
     *
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    boolean addCategory(String categoryName, String categoryIcon);


    /**
     * 更新分类数据
     *
     * @param categoryId
     * @param categoryName
     * @param categoryIcon
     * @return
     */
    boolean updateCategory(Integer categoryId, String categoryName, String categoryIcon);

    /**
     * 批量删除分类数据
     *
     * @param ids
     * @return
     */
    boolean deleteCategories(Integer[] ids);
}
