package cn.sucrelt.sucreblog.dao;

import java.util.List;

/**
 * @description:
 * @author: sucre
 * @date: 2021/01/07
 * @time: 16:19
 */
public interface BlogTagRelationMapper {
    List<Long> selectDistinctTagIds(Integer[] tagIds);
}
