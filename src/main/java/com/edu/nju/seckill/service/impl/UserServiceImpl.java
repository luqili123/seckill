package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.dao.UserMapper;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CurrentUser;
import com.edu.nju.seckill.domain.dto.UserInfo;
import com.edu.nju.seckill.domain.dto.UserParam;
import com.edu.nju.seckill.domain.dto.UserResult;
import com.edu.nju.seckill.exception.DataBaseException;
import com.edu.nju.seckill.exception.PasswordErrorException;
import com.edu.nju.seckill.exception.PhoneNotFoundException;
import com.edu.nju.seckill.exception.PhoneUsedException;
import com.edu.nju.seckill.service.UserService;
import com.edu.nju.seckill.utils.JwtUtil;
import com.edu.nju.seckill.utils.RedisUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author lql
 * @date 2020/1/11 20:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${redis.expire}")
    private long expire;

    /***
     * 判断该手机号是否已经存在（注册）
     * @param phone
     * @return
     */
    @Override
    public boolean hasPhone(String phone) {
        return userMapper.selectByPhone(phone) != null;
    }

    /***
     * 写入用户数据
     * @param userParam
     * @return
     */
    @Override
    public boolean add(UserParam userParam) {
        User user = new User();
        //1.生成随机8位字母的用户名
        user.setName(UUID.randomUUID().toString().substring(0, 8));
        //2.对密码进行加密
        user.setPassword(encoder.encode(userParam.getPassword()));
        //3.设置密码
        user.setPhone(userParam.getPhone());
        //4.设置其他基础信息
        user.setRole(1);
        user.setDeleteFlag(0);
        //4.存入数据库
        if (userMapper.insert(user) != 0) {
            //5.将用户信息存入缓存中
            //to do......
            return true;
        }
        return false;
    }

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.selectByPhone(phone);
    }

    @Override
    public boolean updatePwd(UserParam userParam) {
        return userMapper.updatePwd2(userParam) == 1;
    }

    @Override
    public boolean updateInfo(UserInfo userInfo) {
        return userMapper.updateInfo(userInfo) == 1;
    }

    /**
     * 登录
     *
     * @param userParam 前端传来的登录用户信息
     * @return 返回登录用户信息+Token
     */
    @Override
    public UserResult login(UserParam userParam) {
        User user = getUserByPhone(userParam.getPhone());
        if (user != null) {
            //1.1密码匹配
            if (encoder.matches(userParam.getPassword(), user.getPassword())) {
                //将用户信息存入redis,有效期为半小时
                String token = jwtUtil.generate(user);
                redisUtil.saveUser(user, token, expire);
                UserResult userResult = new UserResult();
                userResult.setUser(user);
                userResult.setToken(token);
                return userResult;
            }
            //1.2密码不匹配
            throw new PasswordErrorException("登录密码错误");
        }
        //2.用户不存在
        throw new PhoneNotFoundException("手机号不存在");
    }

    /**
     * 用户注册
     *
     * @param userParam 前端传来的用户信息
     * @return true/false
     */
    @Override
    public boolean register(UserParam userParam) {
        //1.查询数据库，看该手机号是否存在
        if (hasPhone(userParam.getPhone())) {
            throw new PhoneUsedException("该手机号码已被注册");
        }
        //2.数据库不存在，则插入用户数据
        if (add(userParam)) {
            return true;
        }
        throw new DataBaseException("数据库开小差啦，请稍后重试");
    }

    /**
     * 退出登录
     *
     * @param token 登录令牌
     * @return true/false
     */
    @Override
    public boolean logout(String token) {
        if (redisUtil.delete(token)) {
            return true;
        }
        throw new DataBaseException("服务器开小差啦，请稍后重试");
    }

    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @return true/false
     */
    @Override
    public boolean sendMessage(String phone) {
        if (hasPhone(phone)) {
            // TODO 发送验证码
            return true;
        }
        throw new PhoneNotFoundException("该手机号码未注册");
    }

    /**
     * 校验验证码
     *
     * @param phone
     * @param chkCode
     * @return
     */
    @Override
    public boolean verifyCode(String phone, String chkCode) {
        if (hasPhone(phone)) {
            // TODO 校验验证码逻辑
            return true;
        }
        throw new PhoneNotFoundException("该手机号码未注册");
    }

    @Override
    public boolean resetPassword(UserParam userParam) {
        if (hasPhone(userParam.getPhone())) {
            userParam.setPassword(encoder.encode(userParam.getPassword()));
            return updatePwd(userParam);
        }
        throw new PhoneNotFoundException("非法请求！");
    }

    @Override
    public boolean updateInfo(User user, String token, String name, String email) {
        if (userMapper.updateUser(user.getUid(), name, email) == 1) {
            // 更新Redis用户信息
            user.setName(name);
            user.setEmail(email);
            redisUtil.saveUser(user, token, expire);
            return true;
        }
        throw new DataBaseException("用户信息更新失败");
    }

    @Override
    public boolean updatePwd(User user, String token, String password) {
        String encodePwd = encoder.encode(password);
        if (encoder.matches(password, user.getPassword())) {
            throw new PasswordErrorException("新密码不能和原密码相同");
        }
        if (userMapper.updatePwd(user.getUid(), encoder.encode(password)) == 1) {
            // 删除Redis信息
            redisUtil.del(token);
            return true;
        }
        throw new DataBaseException("数据库开小差了，请稍后重试");
    }
}
