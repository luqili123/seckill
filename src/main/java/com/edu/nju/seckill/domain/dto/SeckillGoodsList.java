package com.edu.nju.seckill.domain.dto;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author lql
 * @date 2020/1/29 15:16
 */
public class SeckillGoodsList implements Serializable {

    @ApiModelProperty(value = "秒杀的商品列表")
    private List<SeckillGoodsResult> seckillGoodsList;

    @ApiModelProperty(value = "秒杀开始时间",required = true,example = "2020-01-20 02:42:35")
    private Date startTime;

    @ApiModelProperty(value = "秒杀结束时间",required = true,example = "2020-02-19 02:42:35")
    private Date endTime;

    public List<SeckillGoodsResult> getSeckillGoodsList() {
        return seckillGoodsList;
    }

    public void setSeckillGoodsList(List<SeckillGoodsResult> seckillGoodsList) {
        this.seckillGoodsList = seckillGoodsList;
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

    @Override
    public String toString() {
        return "SeckillGoodsList{" +
                "seckillGoodsList=" + seckillGoodsList +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
