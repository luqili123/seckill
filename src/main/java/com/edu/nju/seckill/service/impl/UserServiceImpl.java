package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.UserMapper;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lql
 * @date 2020/1/11 20:17
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
}
