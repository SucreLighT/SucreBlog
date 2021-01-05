package cn.sucrelt.sucreblog.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description: 用于封装分页结果的工具类
 * @author: sucre
 * @date: 2021/01/05
 * @time: 15:06
 */
public class PageResult {
    /**
     * 总记录数
     */
    @Setter
    @Getter
    private int totalCount;

    /**
     * 每页记录数
     */
    @Setter
    @Getter
    private int pageSize;

    /**
     * 总页数
     */
    @Setter
    @Getter
    private int totalPage;

    /**
     * 当前页数
     */
    @Setter
    @Getter
    private int currPage;

    /**
     * 列表数据
     */
    @Setter
    @Getter
    private List<?> list;

    public PageResult(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
}
