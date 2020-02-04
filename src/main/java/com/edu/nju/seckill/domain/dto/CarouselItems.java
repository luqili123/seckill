package com.edu.nju.seckill.domain.dto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

@ApiModel("重点商品轮播图实体")
public class CarouselItems implements Serializable {


    @ApiModelProperty(value = "轮播图url",required = true)
    private String imgUrl;

    @ApiModelProperty(value = "goods商品id",required = true)
    private Long goods_id;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Long goods_id) {
        this.goods_id = goods_id;
    }


}
