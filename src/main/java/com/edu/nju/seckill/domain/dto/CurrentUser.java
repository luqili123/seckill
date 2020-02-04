package com.edu.nju.seckill.domain.dto;

import com.edu.nju.seckill.domain.User;
import io.swagger.annotations.ApiModel;

/**
 * @author lql
 * @date 2020/2/4 20:26
 */
@ApiModel("当前登录用户对象")
public class CurrentUser {

    private User user;
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "user=" + user +
                ", token='" + token + '\'' +
                '}';
    }
}
