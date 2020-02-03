package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @Description: 获取地址后端返回结果
* @Author: whn
* @Date: 2020/2/3
*/
@ApiModel(value = "获取地址后端返回结果")
public class GetAddressResult {

    @ApiModelProperty(value = "地址编号")
    private Integer aid;
    @ApiModelProperty(value = "用户编号")
    private Long uid;
    @ApiModelProperty(value = "邮政编码")
    private String postcode;
    @ApiModelProperty(value = "地址信息")
    private String address;
    @ApiModelProperty(value = "收货人姓名")
    private String receiver_name;
    @ApiModelProperty(value = "收货人手机号")
    private String receiver_phone;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiverName() {
        return receiver_name;
    }

    public void setReceiverName(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiverPhone() {
        return receiver_phone;
    }

    public void setReceiverPhone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

}
