package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lql
 * @date 2020/1/11 20:25
 */
@Controller
@Api(tags = "用户控制类")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/hello")
    @ResponseBody
    public CommonResult<User> hello(){
        User user=new User();
        user.setAddressId(10);

        return  CommonResult.success(user);
    };

}
