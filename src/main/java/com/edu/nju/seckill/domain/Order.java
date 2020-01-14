package com.edu.nju.seckill.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lqllq
 */
@ApiModel("订单对象实体")
public class Order implements Serializable {
    @ApiModelProperty(value = "订单编号",required = true)
    private Long oid;
    @ApiModelProperty(value = "用户编号",required = true)
    private Long uid;
    @ApiModelProperty(value = "商品编号",required = true)
    private Long gid;
    @ApiModelProperty(value = "收货人手机号",required = true)
    private String receiverPhone;
    @ApiModelProperty(value = "收货人姓名",required = true)
    private String receiverName;
    @ApiModelProperty(value = "收货人地址",required = true)
    private String address;
    @ApiModelProperty(value = "邮政编码",required = true)
    private String postcode;
    @ApiModelProperty(value = "商品库存")
    private Integer count;
    @ApiModelProperty(value = "商品价格",required = true)
    private Double price;
    @ApiModelProperty(value = "订单创建时间",required = true)
    private Date createTime;
    @ApiModelProperty(value = "订单付款时间",required = true)
    private Date payTime;
    @ApiModelProperty(value = "付款方式",required = true)
    private String payType;
    @ApiModelProperty(value = "发货时间",required = true)
    private Date sendTime;
    @ApiModelProperty(value = "订单状态")
    private Integer status;
    @ApiModelProperty(value = "订单是否为秒杀订单（0代表不是，1代表是）",required = true)
    private Integer seckillFlag;

    public Long getOid() {
        return oid;
    }

    public void setOid(Long oid) {
        this.oid = oid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSeckillFlag() {
        return seckillFlag;
    }

    public void setSeckillFlag(Integer seckillFlag) {
        this.seckillFlag = seckillFlag;
    }
}