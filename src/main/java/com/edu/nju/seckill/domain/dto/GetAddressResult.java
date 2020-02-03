package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

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
    @ApiModelProperty("收货人姓名")
    private String receiver_name;
    @ApiModelProperty("收货人号码")
    private String receiver_phone;

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

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


}
