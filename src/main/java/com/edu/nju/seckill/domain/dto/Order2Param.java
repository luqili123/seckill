package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author LiuWen
 * @version 1.0
 * @name Order2Param
 * @description 创建订单所需信息的对象
 * @date 2020-4-22 0:58
 */
@ApiModel("创建订单所需信息的对象")
public class Order2Param {
    @ApiModelProperty(value = "收货地址id")
    private Integer addressId;
    @ApiModelProperty(value = "商品id，如果seckill_flag=1，则表示普通商品id，如果=2，则表示秒杀商品的id")
    private Long goodsId;
    @ApiModelProperty(value = "付款方式")
    private String payWay;
    @ApiModelProperty(value = "购买商品的数量")
    private Integer num;

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
