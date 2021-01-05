package cn.sucrelt.sucreblog.util;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @description: 设置分页查询参数的工具类
 * @author: sucre
 * @date: 2021/01/05
 * @time: 15:50
 */
public class PageQueryUtil extends LinkedHashMap<String, Object> {
    /**
     * 当前页码
     */
    @Setter
    @Getter
    private int page;

    /**
     * 每页条数
     */
    @Setter
    @Getter
    private int limit;

    public PageQueryUtil(Map<String, Object> params) {
        this.putAll(params);

        //分页参数
        this.page = Integer.parseInt(params.get("page").toString());
        this.limit = Integer.parseInt(params.get("limit").toString());
        this.put("start", (page - 1) * limit);
        this.put("page", page);
        this.put("limit", limit);
    }

    @Override
    public String toString() {
        return "PageUtil{" +
                "page=" + page +
                ", limit=" + limit +
                '}';
    }
}
