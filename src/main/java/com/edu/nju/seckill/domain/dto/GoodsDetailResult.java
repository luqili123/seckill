package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
* @Description: 商品详情页实体
* @Author: whn
* @Date: 2020/2/1
*/
@ApiModel("商品详情页对象实体")
public class GoodsDetailResult {
    @ApiModelProperty(value = "商品编号")
    private Long gid;
    @ApiModelProperty(value = "商品名称")
    private String goods_name;
    @ApiModelProperty(value = "商品价格")
    private Double goods_price;
    @ApiModelProperty(value = "商品描述")
    private String desc;
    @ApiModelProperty(value = "商品图片地址")
    private String imgurl;
    @ApiModelProperty(value = "商品库存")
    private Integer count;
    @ApiModelProperty(value = "上架时间")
    private Date displaytime;
    @ApiModelProperty(value = "该商品是否被收藏")
    private Boolean favorited;

    public Boolean getFavorited() {
        return favorited;
    }

    public void setFavorited(Boolean favorited) {
        this.favorited = favorited;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getDisplaytime() {
        return displaytime;
    }

    public void setDisplaytime(Date displaytime) {
        this.displaytime = displaytime;
    }


}
