package cn.sucrelt.sucreblog.service;

import cn.sucrelt.sucreblog.entity.Blog;

/**
 * @description:
 * @author: sucre
 * @date: 2021/01/11
 * @time: 16:36
 */
public interface BlogService {
    /**
     * 添加一篇博客
     *
     * @param blog
     * @return
     */
    String saveBlog(Blog blog);
}
