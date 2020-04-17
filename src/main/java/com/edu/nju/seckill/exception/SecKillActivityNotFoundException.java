package com.edu.nju.seckill.exception;

/**
 * @author LiuWen
 * @version 1.0
 * @name SecKillActivityNotFoundException
 * @description 当前没有秒杀活动
 * @date 2020-4-17 14:32
 */
public class SecKillActivityNotFoundException extends RuntimeException {
    private String message;

    public SecKillActivityNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
