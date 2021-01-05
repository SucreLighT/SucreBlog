package cn.sucrelt.sucreblog.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @description: 定义后端响应结果数据格式的工具类
 * @author: sucre
 * @date: 2021/01/05
 * @time: 15:09
 */
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 响应码
     */
    @Setter
    @Getter
    private int resultCode;

    /**
     * 响应信息
     */
    @Setter
    @Getter
    private String message;

    /**
     * 响应数据
     */
    @Setter
    @Getter
    private T data;

    public Result() {
    }

    public Result(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
