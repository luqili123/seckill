package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.service.UserService;
import com.edu.nju.seckill.utils.JwtUtil;
import com.edu.nju.seckill.utils.RedisUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    BCryptPasswordEncoder encoder;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtUtil jwtUtil;


    @ApiOperation(value = "用户注册",notes = "传入User对象，存入pone以及password")
    @PostMapping("/users/register")
    public CommonResult<Boolean> register(@RequestBody  User user){

        //1.检查手机号是否已经被注册
        if(userService.hasNumber(user.getPhone())){
            return CommonResult.exit(false);
        }
        //2.插入用户数据,并存入缓存
        if(userService.add(user)){
            return CommonResult.success(true);
        }else {
            return CommonResult.failed("注册失败");
        }

    }

    @PostMapping("/users/login")
    public CommonResult<String> login(@RequestParam(value = "phone",required = true) String phone
            , @RequestParam(value = "password",required = true) String password){

        String token;
        //1.先检查缓存中是否有该用户信息
        if(redisUtil.hHasKey("user:"+phone,phone)){
            User user= (User) redisUtil.hget("user:"+phone,phone);
            //1.1若存在该用户,且密码匹配，则生成token,存入缓存，返回给前端
            if(encoder.matches(password,user.getPassword())){
                token=jwtUtil.generate(phone);
                redisUtil.sSet("token",token);
                return CommonResult.success(token);
            }else {
                return CommonResult.failed("密码错误");
            }
        }else { //2若不存在，则前往数据库查询
            User user=userService.getUserByPhone(phone);
            //2.1若用户数据存在且密码匹配，则将该用户数据存入缓存，生成token
            if(user!=null){
                if(encoder.matches(password,user.getPassword())) {
                    redisUtil.hset("user:" + phone, phone, user);
                    token = jwtUtil.generate(user.getPhone());
                    redisUtil.sSet("token", token);
                    return CommonResult.success(token);
                }else {
                    return CommonResult.failed("密码错误");
                }
            }else {//2.2若数据库不存在，则返回错误
                return CommonResult.failed("用户不存在");
            }
        }
    }

}
