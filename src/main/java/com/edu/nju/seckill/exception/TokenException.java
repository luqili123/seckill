package com.edu.nju.seckill.exception;

import com.edu.nju.seckill.common.CommonResult;

/**
 *
 * @author lql
 * @date 2020/1/17 13:42
 */
public class TokenException extends RuntimeException {

    private CommonResult<?> commonResult;

    public TokenException(CommonResult<?> commonResult) {
        super();
        this.commonResult=commonResult;
    }

    public CommonResult<?> getCommonResult() {
        return commonResult;
    }

    public void setCommonResult(CommonResult<String> commonResult) {
        this.commonResult = commonResult;
    }


}
