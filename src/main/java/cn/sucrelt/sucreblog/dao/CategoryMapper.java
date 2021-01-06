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
     * @param PageQueryUtil
     * @return
     */
    List<Category> getCategoryList(PageQueryUtil PageQueryUtil);

    /**
     * 获取所有的分类数
     *
     * @param PageQueryUtil
     * @return
     */
    int getTotalCategories(PageQueryUtil PageQueryUtil);

    /**
     * 根据分类id查询分类对象
     *
     * @param categoryId
     * @return
     */
    Category selectByCategoryId(Integer categoryId);

    /**
     * 根据分类名查询分类对象
     *
     * @param categoryName
     * @return
     */
    Category selectByCategoryName(String categoryName);

    /**
     * 选择性插入分类数据
     *
     * @param record
     * @return
     */
    int insertSelective(Category category);

    /**
     * 根据分类id选择更新分类数据
     *
     * @param record
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
