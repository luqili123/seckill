package com.edu.nju.seckill.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author lqllq
 */
@ApiModel("用户对象实体")
public class User implements Serializable {

    @ApiModelProperty(value = "用户编号",required = true)
    private Long uid;
    @ApiModelProperty(value = "用户名",required = true)
    private String name;
    @ApiModelProperty(value = "密码",required = true)
    private String password;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "电话号码",required = true)
    private String phone;
    @ApiModelProperty(value = "默认地址编号")
    private Integer addressId;
    @ApiModelProperty(value = "用户角色",required = true)
    private Integer role;
    @ApiModelProperty(value = "词条是否被删除",required = true)
    private Integer deleteFlag;


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", addressId=" + addressId +
                ", role=" + role +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}