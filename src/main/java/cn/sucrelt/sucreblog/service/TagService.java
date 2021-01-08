package cn.sucrelt.sucreblog.service;

import cn.sucrelt.sucreblog.util.PageQueryUtil;
import cn.sucrelt.sucreblog.util.PageResult;

/**
 * @description:
 * @author: sucre
 * @date: 2021/01/07
 * @time: 15:46
 */
public interface TagService {
    /**
     * 获取标签的分页数据
     *
     * @param pageQueryUtil
     * @return
     */
    PageResult getBlogTagPage(PageQueryUtil pageQueryUtil);

    /**
     * 添加一条标签
     *
     * @param tagName
     * @return
     */
    Boolean addTag(String tagName);

    /**
     * 批量删除标签
     *
     * @param ids
     * @return
     */
    Boolean deleteTags(Integer[] ids);
}
