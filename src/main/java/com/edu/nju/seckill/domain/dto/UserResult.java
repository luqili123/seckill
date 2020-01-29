package com.edu.nju.seckill.domain.dto;

import com.edu.nju.seckill.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 *
 * @author lql
 * @date 2020/1/18 17:00
 */
@ApiModel("用户和令牌dto")
public class UserResult implements Serializable {

    @ApiModelProperty(value = "用户对象",required = true)
    private User user;

    @ApiModelProperty(value = "令牌",required = true)
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        user.setPassword(null);
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
        return "UserToken{" +
                "user=" + user +
                ", token='" + token + '\'' +
                '}';
    }
}
