package com.edu.nju.seckill.exception;

/**
 * @author LiuWen
 * @version 1.0
 * @name FavExistException
 * @description TODO
 * @date 2020-4-18 10:39
 */
public class FavExistException extends RuntimeException {
    private String message;

    public FavExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
