package com.edu.nju.seckill.domain.dto;

import com.edu.nju.seckill.annotation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author lql
 * @date 2020/1/18 18:13
 */
@ApiModel("用户dto")
public class UserParam implements Serializable {

    @ApiModelProperty(value = "电话号码",required = true,example = "15651879552")
    @Mobile(required = false)
    private String phone;

    @ApiModelProperty(value = "密码",required = true,example = "123456")
    @NotBlank(message = "密码不能为空")
    private String password;

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
