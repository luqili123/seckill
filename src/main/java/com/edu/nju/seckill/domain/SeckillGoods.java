package com.edu.nju.seckill.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author lqllq
 */
@ApiModel("秒杀商品对象实体")
public class SeckillGoods implements Serializable {

    @ApiModelProperty(value = "秒杀商品编号")
    private Long sgid;

    @ApiModelProperty(value = "秒杀商品名称",required = true)
    @NotNull(message = "name不能为空")
    private String name;

    @ApiModelProperty(value = "商品价格",required = true)
    @NotNull(message = "不能为空")
    private Double price;

    @ApiModelProperty(value = "秒杀商品价格",required = true)
    @NotNull(message = "不能为空")
    private Double seckillPrice;

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

    @ApiModelProperty(value = "秒杀开始时间",required = true,example = "2020-01-20 02:42:35")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Future(message = "请设置未来时间")
    private Date startTime;

    @ApiModelProperty(value = "秒杀结束时间",required = true,example = "2020-02-19 02:42:35")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Future(message = "请设置未来时间")
    private Date endTime;

    @ApiModelProperty(value = "秒杀商品上架时间",required = true,example = "2020-2-20 02:42:35")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date displayTime;

    public SeckillGoods() {
    }

    public SeckillGoods(Long sgid, @NotNull(message = "name不能为空") String name,
                        @NotNull(message = "不能为空") Double price,
                        @NotNull(message = "不能为空") Double seckillPrice,
                        String navName,
                        @NotNull(message = "navType不能为空") String navType,
                        String description,
                        String image,
                        @Min(value = 0, message = "库存必须大于0") @NotNull(message = "Count不能为空") Integer count,
                        @Min(value = 0, message = "库存必须大于0") @NotNull(message = "remainCount不能为空") Integer remainCount,
                        @Future(message = "请设置未来时间") Date startTime, @Future(message = "请设置未来时间") Date endTime,
                        Date displayTime) {
        this.sgid = sgid;
        this.name = name;
        this.price = price;
        this.seckillPrice=seckillPrice;
        this.navName = navName;
        this.navType = navType;
        this.description = description;
        this.image = image;
        this.count = count;
        this.remainCount = remainCount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.displayTime = displayTime;
    }

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

    public Double getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
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

    @Override
    public String toString() {
        return "SeckillGoods{" +
                "sgid=" + sgid +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", seckillPrice=" + seckillPrice +
                ", navName='" + navName + '\'' +
                ", navType='" + navType + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", count=" + count +
                ", remainCount=" + remainCount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", displayTime=" + displayTime +
                '}';
    }
}