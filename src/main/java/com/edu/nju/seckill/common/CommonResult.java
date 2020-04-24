package com.edu.nju.seckill.common;

/**
 * 通用返回对象
 *
 * @author macro
 * @date 2019/4/19
 */
public class CommonResult<T> {
    private long code;
    private String message;
    private T data;

    protected CommonResult() {
    }

    protected CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * 失败返回结果
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized(String message) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), message, null);
    }


    /**
     * 未登录返回结果
     */
    public static <T> CommonResult<T> unauthorized() {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), null);
    }

    /**
     * 未授权返回结果
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    /***
     * 信息已经存在返回结果
     */
    public static <T> CommonResult<T> phoneUsed(String message){
        return new CommonResult<T>(ResultCode.HAS_EXIST.getCode(), message, null);
    }

    public static <T> CommonResult<T> seckillNotFound(String message) {
        return new CommonResult<>(ResultCode.SECKILL_NOT_FOUND.getCode(), message, null);
    }

    /***
     * 数据库异常
     */
    public static <T> CommonResult<T> databaseError(String message){
        return new CommonResult<T>(ResultCode.DATABASE_ERROR.getCode(), message, null);
    }
    public static <T> CommonResult<T> bindingArgsError(String message) {
        return new CommonResult<>(ResultCode.BINDING_ARGS_ERROR.getCode(), message, null);
    }

    public static <T> CommonResult<T> phoneNotFound(String message) {
        return new CommonResult<>(ResultCode.PHONE_NOT_FOUND.getCode(), message, null);
    }

    public static <T> CommonResult<T> passwordError(String message) {
        return new CommonResult<>(ResultCode.PASSWORD_ERROR.getCode(), message, null);
    }

    public static <T> CommonResult<T> goodsNotFound(String message) {
        return new CommonResult<>(ResultCode.GOODS_NOT_FOUND.getCode(), message, null);
    }

    public static <T> CommonResult<T> favHasExist(String message) {
        return new CommonResult<>(ResultCode.FAV_EXIST.getCode(), message, null);
    }

    public static <T> CommonResult<T> createOrderError(String message) {
        return new CommonResult<>(ResultCode.CREATE_ORDER_ERROR.getCode(), message, null);
    }

    public static <T> CommonResult<T> addressNotFound(String message) {
        return new CommonResult<>(ResultCode.ADDRESS_NOT_FOUND.getCode(), message, null);
    }

    public static <T> CommonResult<T> orderNotFound(String message) {
        return new CommonResult<>(ResultCode.ORDER_NOT_FOUND.getCode(), message, null);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
