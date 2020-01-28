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
    @NotNull(message = "name不能为空")
    private String name;

    @ApiModelProperty(value = "秒杀商品价格",required = true)
    @NotNull(message = "不能为空")
    private Double price;

    @ApiModelProperty(value = "所属导航栏名称")
    private String navName;

    @ApiModelProperty(value = "所属导航栏条目英文名称",required = true)
    @NotNull(message = "navType不能为空")
    private String navType;

    @ApiModelProperty(value = "秒杀商品描述")
    private String description;

    @ApiModelProperty(value = "秒杀商品图片地址")
    private String image;

    @ApiModelProperty(value = "秒杀商品总数",required = true)
    @Min(value = 0,message = "库存必须大于0")
    @NotNull(message = "Count不能为空")
    private Integer count;

    @ApiModelProperty(value = "秒杀商品库存",required = true)
    @Min(value = 0,message = "库存必须大于0")
    @NotNull(message = "remainCount不能为空")
    private Integer remainCount;

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

    public String getNavName() {
        return navName;
    }

    public void setNavName(String navName) {
        this.navName = navName;
    }

    public String getNavType() {
        return navType;
    }

    public void setNavType(String navType) {
        this.navType = navType;
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

    @Override
    public String toString() {
        return "SeckillGoodsResult{" +
                "sgid=" + sgid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", navName='" + navName + '\'' +
                ", navType='" + navType + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", count=" + count +
                ", remainCount=" + remainCount +
                '}';
    }
}
