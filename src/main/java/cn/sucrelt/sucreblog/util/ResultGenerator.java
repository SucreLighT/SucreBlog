package cn.sucrelt.sucreblog.util;

/**
 * @description: 生成响应结果的工具类，用于生成Result对象
 * @author: sucre
 * @date: 2021/01/05
 * @time: 15:20
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAILURE_MESSAGE = "FAIL";
    private static final int DEFAULT_SUCCESS_CODE = 200;
    private static final int DEFAULT_FAILURE_CODE = 500;

    public static Result generateSuccessResult() {
        Result result = new Result();
        result.setResultCode(DEFAULT_SUCCESS_CODE);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }

    public static Result generateSuccessResult(String message) {
        Result result = new Result();
        result.setResultCode(DEFAULT_SUCCESS_CODE);
        result.setMessage(message);
        return result;
    }

    public static Result generateSuccessResult(Object data) {
        Result result = new Result();
        result.setResultCode(DEFAULT_SUCCESS_CODE);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    public static Result generateFailResult(String message) {
        Result result = new Result();
        result.setResultCode(DEFAULT_FAILURE_CODE);
        if (message.isEmpty()) {
            result.setMessage(DEFAULT_FAILURE_MESSAGE);
        } else {
            result.setMessage(message);
        }
        return result;
    }

    public static Result generateErrorResult(int code, String message) {
        Result result = new Result();
        result.setResultCode(code);
        result.setMessage(message);
        return result;
    }
}
