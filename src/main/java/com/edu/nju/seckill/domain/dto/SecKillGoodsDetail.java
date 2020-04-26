package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author LiuWen
 * @version 1.0
 * @name SecKillGoodsDetail
 * @description 秒杀商品详情信息
 * @date 2020-4-25 2:59
 */
public class SecKillGoodsDetail {
    @ApiModelProperty("秒杀商品ID")
    private Long sgid;
    @ApiModelProperty("秒杀商品名称")
    private String name;
    @ApiModelProperty("商品价格")
    private Double price;
    @ApiModelProperty("秒杀商品价格")
    private Double secKillPrice;
    @ApiModelProperty("秒杀商品描述")
    private String description;
    @ApiModelProperty("秒杀商品图片")
    private String image;
    @ApiModelProperty("商品数量")
    private Integer count;
    @ApiModelProperty("秒杀商品剩余数量")
    private Integer remainCount;
    @ApiModelProperty("秒杀活动开始时间")
    private Date startTime;
    @ApiModelProperty("秒杀活动结束时间")
    private Date endTime;
    @ApiModelProperty("秒杀商品上架时间")
    private Date displayTime;

    public Long getSgid() {
        return sgid;
    }

    public void setSgid(Long sgid) {
        this.sgid = sgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSecKillPrice() {
        return secKillPrice;
    }

    public void setSecKillPrice(Double secKillPrice) {
        this.secKillPrice = secKillPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(Integer remainCount) {
        this.remainCount = remainCount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(Date displayTime) {
        this.displayTime = displayTime;
    }
}
