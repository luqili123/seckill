package com.edu.nju.seckill.exception;

/**
 * @author LiuWen
 * @version 1.0
 * @name PhoneNotFoundException
 * @description TODO
 * @date 2020-4-15 1:17
 */
public class PhoneNotFoundException extends RuntimeException {
    private String message;

    public PhoneNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
