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
    PageResult getBlogTagPage(PageQueryUtil pageQueryUtil);

    Boolean addTag(String tagName);

    Boolean deleteTags(Integer[] ids);
}
