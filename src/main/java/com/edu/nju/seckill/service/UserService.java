package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.User;

/**
 * @author lql
 * @date 2020/1/11 20:16
 */
public interface UserService {


    public boolean hasNumber(String number);

    public boolean add(User user);
}
