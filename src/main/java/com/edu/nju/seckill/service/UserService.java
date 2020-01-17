package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.User;

/**
 * @author lql
 * @date 2020/1/11 20:16
 */
public interface UserService {

    /***
     * 查找数据库是否存在指定电话号码
     * @param phone
     * @return
     */
    public boolean hasPhone(String phone);

    /***
     * 向用户表添加一条用户信息
     * @param user
     * @return
     */
    public boolean add(User user);

    /***
     * 根据手机号查找用户信息
     * @param phone
     * @return
     */
    public User getUserByPhone(String phone);

}
