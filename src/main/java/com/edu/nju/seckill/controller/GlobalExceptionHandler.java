package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.exception.TokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 * @author lql
 * @date 2020/1/17 13:42
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /***
     * 捕获因没有token或token无法解析的异常
     * @return
     */
    @ExceptionHandler(TokenException.class)
    @ResponseBody
    public CommonResult<?> tokenExceptionHandler(TokenException e){
        return e.getCommonResult();
    }
}
