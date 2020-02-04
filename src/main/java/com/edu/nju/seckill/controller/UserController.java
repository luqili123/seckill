package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CurrentUser;
import com.edu.nju.seckill.domain.dto.UserParam;
import com.edu.nju.seckill.domain.dto.UserResult;
import com.edu.nju.seckill.service.UserService;
import com.edu.nju.seckill.utils.JwtUtil;
import com.edu.nju.seckill.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


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
     * 过期时间(秒)
     */
    private static final long expire=1800;


    @ApiOperation(value = "用户注册",notes = "传入User对象，存入phone以及password")
    @PostMapping("/users/signUp")
    public CommonResult<Boolean> register(@RequestBody  @Validated UserParam userParam, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return CommonResult.validateFailed("手机号格式错误");
        }

         //1.查询数据库，看该手机号是否存在
        if(userService.hasPhone(userParam.getPhone())){
            return CommonResult.exist();
        }else{
            //2.数据库不存在，则插入用户数据
            if(userService.add(userParam)){
                return CommonResult.success(true);
            }else {
                return CommonResult.databaseError();
            }
        }
    }


    @ApiOperation(value = "用户登录",notes = "传入用户手机号和密码")
    @PostMapping("/users/login")
    public CommonResult<UserResult> login(@RequestBody @Validated UserParam userParam, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.toString());
            }
            return CommonResult.validateFailed("手机号格式错误");
        }

        //1.根据手机号码，查询数据库
        User user=userService.getUserByPhone(userParam.getPhone());
        if(user!=null){
            //1.1密码匹配
            if(encoder.matches(userParam.getPassword(),user.getPassword())) {
                //将用户信息存入redis,有效期为半小时
                String token=jwtUtil.generate(user);
                redisUtil.saveUser(user,token,expire);
                System.out.println("登录成功！");
                UserResult userResult =new UserResult();
                userResult.setUser(user);
                userResult.setToken(token);
                return CommonResult.success(userResult);
            }else{
                //1.2密码不匹配
                return CommonResult.validateFailed("密码错误");
            }
        }else {
            //2.用户不存在
            return CommonResult.validateFailed("手机号不存在");
        }

    }

    @GetMapping("/user/logout")
    public CommonResult<?> logout(CurrentUser currentUser){
        //1.先判断用户是否已经登录
        if(currentUser!=null){
            //若已经登录，则退出登录
            try{
                redisUtil.del(currentUser.getToken());
                return CommonResult.success(false);
            }catch (Exception e){
                return CommonResult.validateFailed("token不存在");
            }
        }else {
            //若没有登录，则提示没有登录
            return CommonResult.validateFailed("请登录！");
        }





    }
    @GetMapping("/users/test/{token}")
    public String test(@PathVariable(value = "token") String token){
        User user=redisUtil.getUser(token);
        return user.toString();
    }


}
