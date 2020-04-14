package com.edu.nju.seckill.exception;

/**
 * @author LiuWen
 * @version 1.0
 * @name PhoneUsedException
 * @description TODO
 * @date 2020-4-15 1:31
 */
public class PhoneUsedException extends RuntimeException {
    private String message;

    public PhoneUsedException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
