package com.edu.nju.seckill.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lqllq
 */
@ApiModel("秒杀商品对象实体")
public class SeckillGoods implements Serializable {
    @ApiModelProperty(value = "秒杀商品编号",required = true)
    private Long sgid;
    @ApiModelProperty(value = "秒杀商品名称",required = true)
    private String name;
    @ApiModelProperty(value = "秒杀商品价格",required = true)
    private Double price;
    @ApiModelProperty(value = "秒杀商品类型",required = true)
    private String type;
    @ApiModelProperty(value = "秒杀商品描述")
    private String description;
    @ApiModelProperty(value = "秒杀商品图片地址")
    private String image;
    @ApiModelProperty(value = "秒杀商品总数",required = true)
    private Integer count;
    @ApiModelProperty(value = "秒杀商品库存",required = true)
    private Integer remainCount;
    @ApiModelProperty(value = "秒杀开始时间",required = true)
    private Date startTime;
    @ApiModelProperty(value = "秒杀结束时间",required = true)
    private Date endTime;
    @ApiModelProperty(value = "秒杀商品上架时间",required = true)
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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