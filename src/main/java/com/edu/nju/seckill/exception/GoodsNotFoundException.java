package com.edu.nju.seckill.exception;

/**
 * @author LiuWen
 * @version 1.0
 * @name GoodsNotFoundException
 * @description TODO
 * @date 2020-4-18 2:43
 */
public class GoodsNotFoundException extends RuntimeException {
    private String message;

    public GoodsNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
