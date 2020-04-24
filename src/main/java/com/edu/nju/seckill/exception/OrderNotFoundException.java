package com.edu.nju.seckill.exception;

/**
 * @author LiuWen
 * @version 1.0
 * @name OrderNotFoundException
 * @description 找不到订单 异常
 * @date 2020-4-23 2:43
 */
public class OrderNotFoundException extends RuntimeException {
    private String message;

    public OrderNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
