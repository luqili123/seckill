package com.edu.nju.seckill.exception;

/**
 * @author LiuWen
 * @version 1.0
 * @name AddressNotFoundException
 * @description “地址不存在”异常
 * @date 2020-4-23 0:52
 */
public class AddressNotFoundException extends RuntimeException {
    private String message;

    public AddressNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
