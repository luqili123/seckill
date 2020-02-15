package com.edu.nju.seckill.domain.dto;

import com.edu.nju.seckill.domain.Order;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * 似乎没有进行使用
 * @author lql
 * @date 2020/2/11 11:29
 */
@ApiModel("相同状态的订单")
public class OrderStatusResult {
   private List<Order> orders;

    public OrderStatusResult(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrderStatusResult{" +
                "orders=" + orders +
                '}';
    }
}
