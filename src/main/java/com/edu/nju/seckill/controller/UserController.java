package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CurrentUser;
import com.edu.nju.seckill.domain.dto.UserParam;
import com.edu.nju.seckill.domain.dto.UserResult;
import com.edu.nju.seckill.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lql & Kelvin
 * @date 2020/1/11 20:25
 */
@Api(tags = "用户控制类")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户注册", notes = "传入User对象，存入phone以及password")
    @PostMapping("/register")
    public CommonResult<Boolean> register(@RequestBody @Validated UserParam userParam) {
        boolean res = userService.register(userParam);
        return CommonResult.success(res);
    }

    @ApiOperation(value = "用户登录", notes = "传入用户手机号和密码")
    @PostMapping("/login")
    public CommonResult<UserResult> login(@RequestBody @Validated UserParam userParam) {
        UserResult userResult = userService.login(userParam);
        return CommonResult.success(userResult);
    }

    @ApiOperation(value = "用户退出登录")
    @GetMapping("/logout")
    public CommonResult<Boolean> logout(CurrentUser currentUser) {
        boolean res = userService.logout(currentUser.getToken());
        return CommonResult.success(res);
    }

    @ApiOperation(value = "修改密码")
    @PatchMapping("/resetPwd")
    public CommonResult<?> changePwdWthPwd(@RequestBody UserParam userParam, CurrentUser currentUser) {
        boolean res = userService.updatePwd(currentUser.getUser(),currentUser.getToken(), userParam.getPassword());
        return CommonResult.success(res, "密码修改成功，请重新登录");
    }

    @ApiOperation(value = "根据手机号，发送验证码短信")
    @PostMapping("/chkCode")
    public CommonResult<Boolean> sendMessage(@RequestParam("phone") String phone) {
        boolean res = userService.sendMessage(phone);
        return CommonResult.success(res, "验证码发送成功");
    }

    @ApiOperation(value = "验证验证码是否正确")
    @PostMapping("/verify")
    public CommonResult<Boolean> verify(@RequestParam String phone, @RequestParam String chkCode) {
        boolean res = userService.verifyCode(phone, chkCode);
        return CommonResult.success(res, "验证成功");
    }

    @ApiOperation("找回密码密码")
    @PatchMapping("/password/reset")
    public CommonResult<Boolean> seekPassword(@RequestBody UserParam userParam) {
        boolean res = userService.resetPassword(userParam);
        return CommonResult.success(res, "密码重置成功");
    }

    @ApiOperation(value = "获取登录用户信息")
    @GetMapping("/info")
    public CommonResult<User> userInfo(CurrentUser currentUser) {
        User user = currentUser.getUser();
        user.setPassword(null);
        user.setDeleteFlag(null);
        return CommonResult.success(user);
    }

    @ApiOperation("修改用户信息（邮箱，昵称）")
    @PatchMapping("/update")
    public CommonResult<?> updateUser(CurrentUser currentUser, @RequestParam("name") String name,
                                      @RequestParam("mail") String email) {
        boolean res = userService.updateInfo(currentUser.getUser(), currentUser.getToken(), name, email);
        return CommonResult.success(res, "用户信息更新成功");
    }
}
