package com.edu.nju.seckill.common;

/**
 * 枚举了一些常用API操作码
 *
 * @author macro
 * @date 2019/4/19
 */
public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检验失败"),
    UNAUTHORIZED(401, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    /**
     * 1000 - 1999 参数相关错误
     */
    BINDING_ARGS_ERROR(1002, "参数格式错误"),
    PASSWORD_ERROR(1003, "登录密码错误"),
    /**
     * 2000 - 2999 数据库相关错误
     */
    PHONE_NOT_FOUND(2001, "手机号不存在"),
    HAS_EXIST(2002,"该号码已被注册"),
    DATABASE_ERROR(2003,"数据库异常"),
    SECKILL_NOT_FOUND(2004, "暂时没有秒杀活动"),
    GOODS_NOT_FOUND(2005, "没找到相关商品"),
    CREATE_ORDER_ERROR(2006, "创建订单异常"),
    ORDER_NOT_FOUND(2007, "当前没有订单"),

    /**
     * 3000 - 3999 收藏商品相关的错误
     */
    FAV_EXIST(3001, "该商品已被收藏"),
    FAV_NOT_FOUND(3002,"没有相关收藏"),

    /**
     * 4000 - 4999 收货地址相关的错误
     */
    ADDRESS_NOT_FOUND(4001, "地址不存在")
    ;

    private long code;
    private String message;

    private ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
