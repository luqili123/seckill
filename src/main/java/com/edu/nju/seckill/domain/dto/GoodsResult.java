package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author lql
 * @date 2020/1/29 13:52
 */
@ApiModel(value = "商品返回对象")
public class GoodsResult implements Serializable {

    @ApiModelProperty(value = "商品编号",required = true)
    private Long goods_id;
    @ApiModelProperty(value = "商品名称",required = true)
    private String goods_name;
    @ApiModelProperty(value = "商品价格",required = true)
    private Double goods_price;
    @ApiModelProperty(value = "商品图片地址")
    private String imageUrl;

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public Double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(Double goods_price) {
        this.goods_price = goods_price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "GoodsResult{" +
                "goods_id=" + goods_id +
                ", goods_name='" + goods_name + '\'' +
                ", goods_price=" + goods_price +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
