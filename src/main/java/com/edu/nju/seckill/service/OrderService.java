package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.dto.Order2Param;
import com.edu.nju.seckill.domain.dto.OrderInfoResult;
import com.edu.nju.seckill.domain.dto.OrderSearchResult;

import java.util.List;
import java.util.Map;

/**
 * @author lql
 * @date 2020/1/11 20:19
 */
public interface OrderService {
    /**
    * @Description: 搜索用户订单 通过可选参数keyword进行搜索
    * @Param: [uid, status订单状态,keyword]
    * @return: java.util.List<com.edu.nju.seckill.domain.dto.OrderSearchResult>
    * @Author: whn
    * @Date: 2020/2/12
    */
    List<OrderSearchResult> searchOrder(Long uid, int status,String keyword);

    /***
     * 创建订单
     * @param order
     */
    boolean createOrder(Order order);

    /***
     * 根据订单号删除订单
     * @param oid
     * @return
     */
    boolean deleteByOid(String oid);

    /***
     * 查询订单详情
     * @param oid
     * @return
     */
    OrderInfoResult getOrderInfo(Long uid,String oid);

    /***
     * 按状态查询订单
     * @param status
     * @return
     */
    List<Order> getOrderByStatus(int status);

    /**
     * 创建普通商品的订单
     * @param uid 用户id
     * @param order2Param 订单信息
     * @return true/false
     */
    boolean createOrder(Long uid, Order2Param order2Param);

    /**
     * 获取订单列表
     * @param uid 用户id
     * @param status 订单状态
     * @param keyword 查询关键字
     * @return 订单信息列表
     */
    List<OrderSearchResult> getOrderList(Long uid, Integer status, String keyword);

    /**
     * 创建秒杀订单
     * @param uid 用户id
     * @param order 订单信息
     * @return true/false
     */
    boolean createSecKillOrder(Long uid, Order order);

    boolean insertSecOrder(Map<Object, Object> orderInfo);
}
