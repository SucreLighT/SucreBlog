package cn.sucrelt.sucreblog.dao;

import cn.sucrelt.sucreblog.entity.Category;
import cn.sucrelt.sucreblog.util.PageQueryUtil;

import java.util.List;

/**
 * @description:
 * @author: sucre
 * @date: 2020/12/31
 * @time: 14:14
 */
public interface CategoryMapper {

    /**
     * 获取分类的分页数据
     *
     * @param pageQueryUtil
     * @return
     */
    List<Category> getCategoryList(PageQueryUtil pageQueryUtil);

    /**
     * 获取分类总数
     *
     * @param pageQueryUtil
     * @return
     */
    int getTotalCategories(PageQueryUtil pageQueryUtil);

    /**
     * 根据分类id查询分类数据
     *
     * @param categoryId
     * @return
     */
    Category selectByCategoryId(Integer categoryId);

    /**
     * 根据分类名称查询分类对象
     *
     * @param categoryName
     * @return
     */
    Category selectByCategoryName(String categoryName);

    /**
     * 选择性插入分类数据
     *
     * @param category
     * @return
     */
    int insertSelective(Category category);

    /**
     * 选择性根据分类id更新分类数据
     *
     * @param category
     * @return
     */
    int updateByCategoryIdSelective(Category category);

    /**
     * 批量删除分类数据
     *
     * @param ids
     * @return
     */
    int deleteCategories(Integer[] ids);
}
