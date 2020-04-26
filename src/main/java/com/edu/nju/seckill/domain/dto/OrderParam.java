package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author lql
 * @date 2020/2/9 18:42
 */
@ApiModel("订单参数类")
public class OrderParam {

    @ApiModelProperty(value = "商品编号",required = true)
    private Long gid;

    @ApiModelProperty(value = "收货人手机号",required = true)
    private String receiverPhone;

    @ApiModelProperty(value = "收货人姓名",required = true)
    private String receiverName;

    @ApiModelProperty(value = "地址信息")
    private String address;

    @ApiModelProperty(value = "邮政编码",required = true)
    private String postcode;

    @ApiModelProperty(value = "购买数量",required = true)
    private int count;

    @ApiModelProperty(value = "商品单价",required = true)
    private Double price;

    public OrderParam(Long gid, String receiverPhone, String receiverName, String address, String postcode, int count, Double price) {
        this.gid = gid;
        this.receiverPhone = receiverPhone;
        this.receiverName = receiverName;
        this.address = address;
        this.postcode = postcode;
        this.count = count;
        this.price = price;
    }
    public OrderParam(){}

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Override
    public String toString() {
        return "OrderParam{" +
                "gid=" + gid +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}
