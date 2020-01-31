package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
* @Description:商品列表返回结果实体
* @Author: whn对接zmz
* @Date: 2020/1/31
*/
@ApiModel(value = "商品列表结果实体")
public class GoodsListResult {


    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @ApiModelProperty(value = "商品主键")
    private long gid;
    @ApiModelProperty(value = "商品名称")
    private String goods_name;
    @ApiModelProperty(value = "商品描述")
    private String desc;
    @ApiModelProperty(value = "商品价格")
    private double goods_price;

    @ApiModelProperty(value = "商品展示图路径")
    private String imgUrl;
    @ApiModelProperty(value = "商品销量")
    private int count;
}
