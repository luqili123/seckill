package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;
import java.util.UUID;

/**
 * @author lql
 * @date 2020/1/11 20:25
 */
@RestController
@Api(tags = "用户控制类")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册",notes = "传入User对象，存入pone以及password")
    @PostMapping("/users")
    public CommonResult<Boolean> register(@RequestBody  User user){

        //1.检查手机号是否已经被注册
        if(userService.hasNumber(user.getPhone())){
            return CommonResult.exit(false);
        }
        //2.插入用户数据
        if(userService.add(user)){
            return CommonResult.success(true);
        }else {
            return CommonResult.failed("注册失败");
        }

    }

}
