package cn.sucrelt.sucreblog.dao;

import cn.sucrelt.sucreblog.entity.Blog;

/**
 * @description:
 * @author: sucre
 * @date: 2021/01/11
 * @time: 16:42
 */
public interface BlogMapper {
    /**
     * 条件插入博客数据
     *
     * @param blog
     * @return
     */
    int insertSelective(Blog blog);
}
