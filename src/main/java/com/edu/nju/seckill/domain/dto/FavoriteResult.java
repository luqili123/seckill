package com.edu.nju.seckill.domain.dto;

/**
* @Description:关键词模糊查询返回结果
* @Author: whn
* @Date: 2020/1/30
*/
public class FavoriteResult {
    String imgUrl;
    String goods_name;
    double goods_price;

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

    public double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(double goods_price) {
        this.goods_price = goods_price;
    }



}
