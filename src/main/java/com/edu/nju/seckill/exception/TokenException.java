package com.edu.nju.seckill.exception;

import com.edu.nju.seckill.common.CommonResult;

/**
 *
 * @author lql
 * @date 2020/1/17 13:42
 */
public class TokenException extends RuntimeException {
    private String message;

    public TokenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
