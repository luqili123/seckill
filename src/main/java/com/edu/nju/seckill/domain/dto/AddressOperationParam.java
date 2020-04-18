package com.edu.nju.seckill.domain.dto;

import com.edu.nju.seckill.annotation.Mobile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Pattern;

/**
* @Description: 新增地址接口前端请求参数
* @Author: whn
* @Date: 2020/2/3
*/
@ApiModel(value = "新增地址前端参数实体")
public class AddressOperationParam {

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

    @ApiModelProperty(value = "邮编")
    @Pattern(regexp = "[1-9]\\d{5}",message = "邮编格式错误")
    private String postcode;
    @ApiModelProperty(value = "收货地址")
    private String address;
    @ApiModelProperty(value = "收货人姓名")
    private String receiver_name;

    @ApiModelProperty(value = "收货人电话")
    @Mobile
    private String receiver_phone;
}
