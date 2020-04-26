package com.edu.nju.seckill.domain;

import com.edu.nju.seckill.domain.dto.OrderParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.omg.CORBA.ORB;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author lqllq
 */
@ApiModel("订单对象实体")
public class Order implements Serializable {

    @ApiModelProperty(value = "订单编号",required = true)
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @ApiModelProperty(value = "用户编号",required = true)
    private Long uid;

    @ApiModelProperty(value = "商品编号",required = true)
    private Long gid;

    @ApiModelProperty(value = "收货人手机号",required = true)
    private String receiverPhone;

    @ApiModelProperty(value = "收货人姓名",required = true)
    private String receiverName;

    @ApiModelProperty(value = "收货人地址",required = true)
    private String address;

    @ApiModelProperty(value = "邮政编码",required = true)
    private String postcode;

    @ApiModelProperty(value = "商品库存")
    private Integer count;

    @ApiModelProperty(value = "商品价格",required = true)
    private Double price;

    @ApiModelProperty(value = "订单创建时间",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    @ApiModelProperty(value = "订单付款时间",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payTime;

    @ApiModelProperty(value = "付款方式",required = true)
    private String payType;

    @ApiModelProperty(value = "发货时间",required = true)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sendTime;

    @ApiModelProperty(value = "订单状态")
    private Integer status;

    @ApiModelProperty(value = "订单是否为秒杀订单（1代表不是，2代表是）",required = true)
    private Integer seckillFlag;

    /**
     * 订单构造函数
     * @param oid 订单号
     * @param uid 用户id
     * @param num 购买商品数量
     * @param payWay 支付方式
     * @param goods 商品信息
     * @param address 收货地址
     * @param seckillFlag 是否是秒杀
     */
    public Order(String oid, Long uid, Integer num, String payWay, Goods goods, Address address, Integer seckillFlag) {
        this.oid = oid;
        this.uid = uid;
        this.gid = goods.getGid();
        this.receiverPhone = address.getReceiverPhone();
        this.receiverName = address.getReceiverName();
        this.address = address.getAddress();
        this.postcode = address.getPostcode();
        this.count = num;
        this.price = goods.getPrice();
        this.createTime = new Date();
        this.payTime = new Date();
        this.payType = payWay;
        this.sendTime = new Date();
        this.status = 2; // 已付款
        this.seckillFlag = seckillFlag;
    }

    public Order(OrderParam orderParam,long uid,String oid) {
        this.oid=oid;
        this.uid=uid;
        this.gid=orderParam.getGid();
        this.receiverName=orderParam.getReceiverName();
        this.receiverPhone=orderParam.getReceiverPhone();
        this.address=orderParam.getAddress();
        this.postcode=orderParam.getPostcode();
        this.count=orderParam.getCount();
        this.price=orderParam.getPrice();
        this.createTime=new Date(System.currentTimeMillis());
        this.payTime=null;
        this.payType=null;
        this.sendTime=null;
        this.status=1;//代付款
        this.seckillFlag=1;//秒杀商品
    }

    public Order(String oid, Long uid, Long gid, String receiverPhone, String receiverName
            , String address, String postcode, Integer count, Double price, Date createTime
            , Date payTime, String payType, Date sendTime, Integer status, Integer seckillFlag) {
        this.oid = oid;
        this.uid = uid;
        this.gid = gid;
        this.receiverPhone = receiverPhone;
        this.receiverName = receiverName;
        this.address = address;
        this.postcode = postcode;
        this.count = count;
        this.price = price;
        this.createTime = createTime;
        this.payTime = payTime;
        this.payType = payType;
        this.sendTime = sendTime;
        this.status = status;
        this.seckillFlag = seckillFlag;
    }

    public Order() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getGid() {
        return gid;
    }

    public void setGid(Long gid) {
        this.gid = gid;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSeckillFlag() {
        return seckillFlag;
    }

    public void setSeckillFlag(Integer seckillFlag) {
        this.seckillFlag = seckillFlag;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", uid=" + uid +
                ", gid=" + gid +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", address='" + address + '\'' +
                ", postcode='" + postcode + '\'' +
                ", count=" + count +
                ", price=" + price +
                ", createTime=" + createTime +
                ", payTime=" + payTime +
                ", payType='" + payType + '\'' +
                ", sendTime=" + sendTime +
                ", status=" + status +
                ", seckillFlag=" + seckillFlag +
                '}';
    }
}