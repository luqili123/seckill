package com.edu.nju.seckill.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author lqllq
 */
@ApiModel("地址对象实体")
public class Address implements Serializable {
    @ApiModelProperty(value = "地址编号",required = true)
    private Integer aid;
    @ApiModelProperty(value = "用户编号",required = true)
    private Long uid;
    @ApiModelProperty(value = "邮政编码",required = true)
    private String postcode;
    @ApiModelProperty(value = "地址信息")
    private String address;
    @ApiModelProperty(value = "收货人姓名",required = true)
    private String receiverName;
    @ApiModelProperty(value = "收货人手机号",required = true)
    private String receiverPhone;

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
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}