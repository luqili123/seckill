package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author lql
 * @date 2020/1/28 14:19
 */
@ApiModel("秒杀商品返回类")
public class SeckillGoodsResult implements Serializable {

    @ApiModelProperty(value = "秒杀商品编号")
    private Long sgid;

    @ApiModelProperty(value = "秒杀商品名称",required = true)
    @NotNull(message = "goods_name")
    private String goods_name;

    @ApiModelProperty(value = "秒杀商品描述")
    private String desc;

    @ApiModelProperty(value = "商品原本价格",required = true)
    @NotNull(message = "不能为空")
    private Double goods_price;

    @ApiModelProperty(value = "秒杀时商品价格",required = true)
    @NotNull(message = "不能为空")
    private Double seckill_price;


    @ApiModelProperty(value = "秒杀商品图片地址")
    private String imgUrl;

    @ApiModelProperty(value = "秒杀商品总数",required = true)
    @Min(value = 0,message = "库存必须大于0")
    @NotNull(message = "Count不能为空")
    private Integer count;

    @ApiModelProperty(value = "秒杀商品库存",required = true)
    @Min(value = 0,message = "库存必须大于0")
    @NotNull(message = "remainCount不能为空")
    private Integer remain_count;

    public Long getSgid() {
        return sgid;
    }

    public void setSgid(Long sgid) {
        this.sgid = sgid;
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

    public Double getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(Double goods_price) {
        this.goods_price = goods_price;
    }

    public Double getSeckill_price() {
        return seckill_price;
    }

    public void setSeckill_price(Double seckill_price) {
        this.seckill_price = seckill_price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRemain_count() {
        return remain_count;
    }

    public void setRemain_count(Integer remain_count) {
        this.remain_count = remain_count;
    }

    @Override
    public String toString() {
        return "SeckillGoodsResult{" +
                "sgid=" + sgid +
                ", goods_name='" + goods_name + '\'' +
                ", desc='" + desc + '\'' +
                ", goods_price=" + goods_price +
                ", seckill_price=" + seckill_price +
                ", imgUrl='" + imgUrl + '\'' +
                ", count=" + count +
                ", remain_count=" + remain_count +
                '}';
    }
}
