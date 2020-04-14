package com.edu.nju.seckill.exception;

/**
 * @author LiuWen
 * @version 1.0
 * @name PasswordErrorException
 * @description TODO
 * @date 2020-4-15 1:19
 */
public class PasswordErrorException extends RuntimeException {
    private String message;

    public PasswordErrorException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
