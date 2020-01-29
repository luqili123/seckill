package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author lql
 * @date 2020/1/18 18:13
 */
@ApiModel("用户dto")
public class UserParam implements Serializable {

    @ApiModelProperty(value = "密码",required = true)
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "电话号码")
    @Pattern(regexp = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$"
            ,message = "手机号格式错误")
    private String phone;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
