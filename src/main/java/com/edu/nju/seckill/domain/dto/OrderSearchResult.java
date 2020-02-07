package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel("订单页——搜索订单项")
public class OrderSearchResult {


    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(Double goods_price) {
        this.goods_price = goods_price;
    }

    public String getReceiver_name() {
        return receiver_name;
    }

    public void setReceiver_name(String receiver_name) {
        this.receiver_name = receiver_name;
    }

    public Integer getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Integer order_number) {
        this.order_number = order_number;
    }

    public Date getCreate_name() {
        return create_name;
    }

    public void setCreate_name(Date create_name) {
        this.create_name = create_name;
    }

    @ApiModelProperty("gid")
    Long goods_id;
    @ApiModelProperty("图片路径")
    String imgUrl;
    @ApiModelProperty("商品价格")
    Double goods_price;
    @ApiModelProperty("收货人")
    String receiver_name;
    @ApiModelProperty("购买数量")
    Integer order_number;
    @ApiModelProperty("订单创建时间")
    Date create_name;

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    @ApiModelProperty("商品名")
    String goods_name;


}
