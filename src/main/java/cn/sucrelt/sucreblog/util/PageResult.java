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
    @Getter
    @Setter
    private int totalCount;
    /**
     * 每页记录数
     */
    @Getter
    @Setter
    private int pageSize;
    /**
     * 总页数
     */
    @Getter
    @Setter
    private int totalPage;
    /**
     * 当前页数
     */
    @Getter
    @Setter
    private int currPage;
    /**
     * 列表数据
     */
    @Getter
    @Setter
    private List<?> list;

    /**
     * 分页
     *
     * @param list       列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public PageResult(List<?> list, int totalCount, int pageSize, int currPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
}
