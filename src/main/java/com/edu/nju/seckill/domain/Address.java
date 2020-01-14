package com.edu.nju.seckill.domain;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author lqllq
 */
@ApiModel("地址对象实体")
public class Address implements Serializable {
    private Integer aid;

    private Long uid;

    private String postcode;

    private String address;

    private String receiverName;

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