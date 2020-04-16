package com.edu.nju.seckill.domain.dto;

import com.edu.nju.seckill.annotation.Mobile;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author lql
 * @date 2020/2/6 19:59
 */
public class UserInfo implements Serializable {

    @ApiModelProperty(value = "电话号码")
    @Mobile
    private String phone;

    @ApiModelProperty(value = "用户名")
    private String name;

//    ,regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"
    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式错误")
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
