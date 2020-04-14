package com.edu.nju.seckill.exception;

/**
 * @author LiuWen
 * @version 1.0
 * @name DataBaseException
 * @description TODO
 * @date 2020-4-15 1:36
 */
public class DataBaseException extends RuntimeException {
    private String message;

    public DataBaseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
