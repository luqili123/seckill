package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.service.UserService;
import com.edu.nju.seckill.utils.JwtUtil;
import com.edu.nju.seckill.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


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

    /***
     * 过期时间
     */
    private static final long expire=1800000;


    @ApiOperation(value = "用户注册",notes = "传入User对象，存入phone以及password")
    @PostMapping("/users/register")
    public CommonResult<Boolean> register(@RequestBody  User user){
        //1.查询数据库，看该手机号是否存在
        if(userService.hasPhone(user.getPhone())){
            return CommonResult.exist(false);
        }else{
            //2.数据库不存在，则插入用户数据
            if(userService.add(user)){
                return CommonResult.success(true);
            }else {
                return CommonResult.databaseError(false);
            }
        }
    }


    @ApiOperation(value = "用户登录",notes = "传入用户手机号和密码")
    @PostMapping("/users/login")
    public CommonResult<String> login(@RequestParam(value = "phone",required = true) String phone
            , @RequestParam(value = "password",required = true) String password){

        //1.根据手机号码，查询数据库
        User user=userService.getUserByPhone(phone);
        if(user!=null){
            //1.1密码匹配
            if(encoder.matches(password,user.getPassword())) {
                //将用户信息存入redis,有效期为半小时
                String token=jwtUtil.generate(phone);
                redisUtil.saveUser(user,token,expire);
                System.out.println("登录成功！");
                return CommonResult.success(token);
            }else{
                //1.2密码不匹配
                return CommonResult.failed("密码错误");
            }
        }else {
            //2.用户不存在
            return CommonResult.unauthorized("用户不存在");
        }

    }

    @GetMapping("/users/test")
    public String test(){
        return "test";
    }
}
