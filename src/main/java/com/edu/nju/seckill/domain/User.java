package com.edu.nju.seckill.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * @author lqllq
 */
@ApiModel("用户对象实体")
public class User implements Serializable {

    @ApiModelProperty(value = "用户编号")
    private Long uid;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "邮箱")
    @Email(message = "邮箱格式错误"
            ,regexp = "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$")
    private String email;

    @ApiModelProperty(value = "电话号码")
    @Pattern(regexp = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$"
            ,message = "手机号格式错误")
    private String phone;

    @ApiModelProperty(value = "默认地址编号")
    @Min(value = 1,message = "地址编号错误")
    private Integer addressId;

    @ApiModelProperty(value = "用户角色")
    @Min(value = 0,message = "非法角色")
    @Max(value = 2,message = "非法角色")
    private Integer role;

    @ApiModelProperty(value = "词条是否被删除")
    @Min(value = 0,message = "非法deleteFlag")
    @Max(value = 1,message = "非法deleteFlag")
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