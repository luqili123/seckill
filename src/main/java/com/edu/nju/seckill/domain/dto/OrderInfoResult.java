package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("订单详情页信息结果")
public class OrderInfoResult {
    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
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

    public Double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(Double goods_price) {
        this.goods_price = goods_price;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
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

    public Integer getSeckill_flag() {
        return seckill_flag;
    }

    public void setSeckill_flag(Integer seckill_flag) {
        this.seckill_flag = seckill_flag;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    @ApiModelProperty(value = "订单编号",required = true)
    private String oid;

    @ApiModelProperty(value = "用户编号")
    private Long uid;
    @ApiModelProperty(value = "商品编号",required = true)
    private Long goods_id;

    @ApiModelProperty(value = "收货人手机号",required = true)
    private String receiver_phone;

    @ApiModelProperty(value = "收货人姓名",required = true)
    private String receiver_name;

    @ApiModelProperty(value = "收货人地址",required = true)
    private String address;

    @ApiModelProperty(value = "邮政编码",required = true)
    private String postcode;

    @ApiModelProperty(value = "商品数量")
    private Integer count;

    @ApiModelProperty(value = "商品价格",required = true)
    private Double goods_price;

    @ApiModelProperty(value = "订单创建时间",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date create_time;

    @ApiModelProperty(value = "订单付款时间",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payTime;

    @ApiModelProperty(value = "付款方式",required = true)
    private String pay_type;

    @ApiModelProperty(value = "发货时间",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sendTime;

    @ApiModelProperty(value = "订单状态")
    private Integer status;

    @ApiModelProperty(value = "是否是秒杀商品")
    private Integer seckill_flag;

    @ApiModelProperty(value = "商品图片")
    private String imgUrl;

    @ApiModelProperty(value = "商品名")
    private String goods_name;
}
