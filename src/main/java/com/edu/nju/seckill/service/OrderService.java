package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.dto.OrderSearchResult;

import java.util.List;

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
    boolean deleteByOid(long oid);

    /***
     * 查询订单详情
     * @param oid
     * @return
     */
    Order getOrderInfo(long oid);

    /***
     * 按状态查询订单
     * @param status
     * @return
     */
    List<Order> getOrderByStatus(int status);

}
