package com.edu.nju.seckill.exception;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.exception.TokenException;
import io.lettuce.core.RedisCommandTimeoutException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult<?> tokenExceptionHandler(Exception e){
        if (e instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) e).getBindingResult();
            List<ObjectError> errors = result.getAllErrors();
            ObjectError error = errors.get(0);
            return CommonResult.bindingArgsError(error.getDefaultMessage());
        } else if (e instanceof TokenException) {
            return CommonResult.unauthorized(e.getMessage());
        } else if (e instanceof PhoneNotFoundException) {
            return CommonResult.phoneNotFound(e.getMessage());
        } else if (e instanceof PasswordErrorException) {
            return CommonResult.passwordError(e.getMessage());
        } else if (e instanceof PhoneUsedException) {
            return CommonResult.phoneUsed(e.getMessage());
        } else if (e instanceof DataBaseException) {
            return CommonResult.databaseError(e.getMessage());
        } else if (e instanceof RedisCommandTimeoutException) {
            return CommonResult.databaseError("网络超时，请稍后重试");
        } else if (e instanceof SecKillActivityNotFoundException) {
            return CommonResult.seckillNotFound(e.getMessage());
        } else if (e instanceof  GoodsNotFoundException) {
            return CommonResult.goodsNotFound(e.getMessage());
        } else if (e instanceof FavExistException) {
            return CommonResult.favHasExist(e.getMessage());
        }
        return null;
    }
}
