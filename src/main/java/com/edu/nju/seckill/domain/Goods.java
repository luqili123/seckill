package com.edu.nju.seckill.domain;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lqllq
 */
@ApiModel("普通商品对象实体")
public class Goods implements Serializable {
    private Long gid;

    private String name;

    private Double price;

    private String type;

    private String description;

    private String image;

    private Integer count;

    private Date displayTime;

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

    public Date getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(Date displayTime) {
        this.displayTime = displayTime;
    }
}