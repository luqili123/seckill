package com.edu.nju.seckill.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
/**
 * @author lqllq
 */
@ApiModel("普通商品对象实体")
public class Goods implements Serializable {
    @ApiModelProperty(value = "商品编号",required = true)
    private Long gid;
    @ApiModelProperty(value = "商品名称",required = true)
    private String name;
    @ApiModelProperty(value = "商品价格",required = true)
    private Double price;
    @ApiModelProperty(value = "导航编号",required = true)
    private Integer nid;
    @ApiModelProperty(value = "商品描述")
    private String description;
    @ApiModelProperty(value = "商品图片地址")
    private String image;
    @ApiModelProperty(value = "商品库存",required = true)
    private Integer count;
    @ApiModelProperty(value = "上架时间",required = true)
    private Date displayTime;

    @ApiModelProperty(value = "是否上首页推荐，1不推荐；2推荐")
    private Short recommend;

    public Short getRecommend() {
        return recommend;
    }

    public void setRecommend(Short recommend) {
        this.recommend = recommend;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
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

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
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

    public Date getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(Date displayTime) {
        this.displayTime = displayTime;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "gid=" + gid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", nid=" + nid +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", count=" + count +
                ", displayTime=" + displayTime +
                '}';
    }
}