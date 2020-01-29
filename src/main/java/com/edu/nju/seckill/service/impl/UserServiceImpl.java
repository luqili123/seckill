package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.UserMapper;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.UserParam;
import com.edu.nju.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
    BCryptPasswordEncoder encoder;

    @Autowired
    private HttpServletRequest request;

    /***
     * 判断该手机号是否已经存在（注册）
     * @param phone
     * @return
     */
    @Override
    public boolean hasPhone(String phone) {
        return userMapper.selectByPhone(phone)==null?false:true;
    }

    /***
     * 写入用户数据
     * @param userParam
     * @return
     */
    @Override
    public boolean add(UserParam userParam) {
        User user=new User();
        //1.生成随机8位字母的用户名
        user.setName(UUID.randomUUID().toString().substring(0,8));
        //2.对密码进行加密
        user.setPassword(encoder.encode(userParam.getPassword()));
        //3.设置密码
        user.setPhone(userParam.getPhone());
        //4.设置其他基础信息
        user.setRole(1);
        user.setDeleteFlag(0);
        //4.存入数据库
        if(userMapper.insert(user)!=0){
            //5.将用户信息存入缓存中
            //to do......
           return true;
        }
        return  false;
    }

    @Override
    public User getUserByPhone(String phone) {
        User user=userMapper.selectByPhone(phone);
        if(user!=null&&user.getPassword()!=null) {
            return user;
        }else {
            return null;
        }
    }


}
