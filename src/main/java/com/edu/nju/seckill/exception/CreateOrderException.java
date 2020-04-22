package com.edu.nju.seckill.exception;

/**
 * @author LiuWen
 * @version 1.0
 * @name CreateOrderException
 * @description 创建订单异常
 * @date 2020-4-23 0:08
 */
public class CreateOrderException extends RuntimeException {
    private String message;

    public CreateOrderException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
