package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CurrentUser;
import com.edu.nju.seckill.domain.dto.UserInfo;
import com.edu.nju.seckill.domain.dto.UserParam;
import com.edu.nju.seckill.domain.dto.UserResult;
import com.edu.nju.seckill.service.UserService;
import com.edu.nju.seckill.utils.JwtUtil;
import com.edu.nju.seckill.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;


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
    @PostMapping("/user/signUp")
    public CommonResult<Boolean> register(@RequestBody  @Validated UserParam userParam, BindingResult bindingResult){

        if(bindingResult.hasErrors()) {
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
    @GetMapping("/test/redis/{token}")
    public CommonResult<?> testRedis(@PathVariable String token) {
        User user = redisUtil.getUser(token);
        redisUtil.set(UUID.randomUUID().toString(), "i am robot", 60 * 30);
        return CommonResult.success(user);
    }

    @ApiOperation(value = "用户登录",notes = "传入用户手机号和密码")
    @PostMapping("/user/login")
    public CommonResult<UserResult> login(@RequestBody @Validated UserParam userParam
            , BindingResult bindingResult){

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

    @ApiOperation(value = "用户退出登录")
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
    @GetMapping("/user/test/{token}")
    public String test(@PathVariable(value = "token") String token){
        User user=redisUtil.getUser(token);
        return user.toString();
    }

    @ApiOperation(value = "修改密码")
    @PatchMapping("/user/resetpwd")
    public CommonResult<?> changePwdWthPwd(@RequestBody UserParam userParam,CurrentUser currentUser){
        //改进：数据库和redis都要操作成功，这里应该加事务防止部分成功,若失败则抛出全局异常
        if(currentUser!=null) {
            String encodePwd=encoder.encode(userParam.getPassword());
            if(encoder.matches(userParam.getPassword(),currentUser.getUser().getPassword())){
                return CommonResult.validateFailed("不能和原始密码相同！");
            }else{
                try{
                    //新密码加密
                    userParam.setPassword(encodePwd);
                    //修改数据库密码
                    if(userService.updatePwd(userParam)){
                        //删除redis缓存
                        redisUtil.del(currentUser.getToken());
                        return CommonResult.success("修改成功！");
                    }else {
                        return CommonResult.validateFailed();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return CommonResult.failed("操作失败，请稍后再试!");
                }
            }

        }else {
            return CommonResult.unauthorized("未登录!");
        }

    }

    @ApiOperation(value = "根据手机号，发送验证码短信")
    @PostMapping("/user/chkCode")
    public CommonResult<?> sendMessage(@RequestBody String phone){
        if(userService.hasPhone(phone)){
            //发送验证码
            return CommonResult.success("发送成功！");
        }else {
            return CommonResult.validateFailed("手机号码未注册或不存在");
        }
    }

    @ApiOperation(value = "验证验证码是否正确")
    @PostMapping("/user/verify")
    public CommonResult<?> verify(@RequestBody String phone,@RequestBody String chkCode){
        return CommonResult.success("输入正确！");
    }

    @ApiOperation("重置密码,这是个危险方法，后面解决")
    @PatchMapping("/user/sendpwd")
    public CommonResult<?> resetPassword(@RequestBody UserParam userParam,CurrentUser currentUser){
        try{

            //1.修改数据库
            userParam.setPassword(encoder.encode(userParam.getPassword()));
            if(userService.updatePwd(userParam)){
                if(redisUtil.hasKey(currentUser.getToken())){
                    //2.删除redis缓存
                    redisUtil.del(currentUser.getToken());
                }
                return CommonResult.success("修改成功！");

            }else {
                return CommonResult.validateFailed();
            }

        }catch (Exception e){
            e.printStackTrace();
            return CommonResult.failed();
        }
    }


    @ApiOperation("修改用户信息（邮箱，昵称）")
    @PatchMapping("/user/info")
    public CommonResult<?> resetPassword(@RequestBody @Validated UserInfo userInfo, CurrentUser currentUser
            , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            //可删
            List<ObjectError> list = bindingResult.getAllErrors();
            for (ObjectError error : list) {
                System.out.println(error.toString());
            }
            return CommonResult.validateFailed("格式错误");
        }
        if(currentUser!=null){
            userInfo.setPhone(currentUser.getUser().getPhone());
            if(userService.updateInfo(userInfo)){
                currentUser.getUser().setEmail(userInfo.getEmail());
                currentUser.getUser().setName(userInfo.getName());
                //生成新token
                String token=jwtUtil.generate(currentUser.getUser());
                redisUtil.saveUser(currentUser.getUser(),token,expire);
                if(redisUtil.hasKey(currentUser.getToken())){
                    redisUtil.del(currentUser.getToken());
                }
                currentUser.setToken(token);
                return CommonResult.success(token);
            }else {
                return CommonResult.validateFailed();
            }
        }else {
            return CommonResult.unauthorized();
        }

    }


}
