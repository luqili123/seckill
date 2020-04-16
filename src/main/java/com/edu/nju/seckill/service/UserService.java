package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CurrentUser;
import com.edu.nju.seckill.domain.dto.UserInfo;
import com.edu.nju.seckill.domain.dto.UserParam;
import com.edu.nju.seckill.domain.dto.UserResult;

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
    public boolean add(UserParam user);

    /***
     * 根据手机号查找用户信息
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);

    /***
     * 更新用户名和邮箱
     * @param userInfo
     * @return
     */
    boolean updateInfo(UserInfo userInfo);

    UserResult login(UserParam userParam);

    boolean register(UserParam userParam);

    boolean logout(String token);

    boolean sendMessage(String phone);

    boolean verifyCode(String phone, String chkCode);

    /**
     * 重置密码
     * @param userParam 重置密码的用户
     * @return true/false
     */
    boolean resetPassword(UserParam userParam);

    boolean updateInfo(User user, String token, String name, String email);

    boolean updatePwd(User user, String token, String password);

    boolean updatePwd(UserParam userParam);
}
