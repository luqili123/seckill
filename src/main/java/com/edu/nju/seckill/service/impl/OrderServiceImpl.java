package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.OrderMapper;
import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.dto.OrderSearchResult;
import com.edu.nju.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:21
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    /**
    * @Description: 通过状态以及关键字搜索订单
    * @Param: [uid, status, keyword]
    * @return: java.util.List<com.edu.nju.seckill.domain.dto.OrderSearchResult>
    * @Author: whn
    * @Date: 2020/2/12
    */
    @Override
    public List<OrderSearchResult> searchOrder(Long uid, int status, String keyword) {
        if(status==5)
            //返回所有数据
            return orderMapper.searchOrder(uid,status,keyword);
        else
            //通过status筛选
            return orderMapper.searchOrderViaStatus(uid,status,keyword);
    }
    
    

    @Override
    public boolean createOrder(Order order) {
        return orderMapper.insert(order)==1;
    }

    @Override
    public boolean deleteByOid(String oid) {
        return orderMapper.deleteByPrimaryKey(oid)==1;
    }

    @Override
    public Order getOrderInfo(String oid) {
        Order order=orderMapper.selectByPrimaryKey(oid);
        if(order!=null){
            return order;
        }
        return null;
    }

    @Override
    public List<Order> getOrderByStatus(int status) {
        List<Order> orders=orderMapper.selectByStatus(status);
        if(orders!=null&&orders.size()>0){
            return orders;
        }
        return null;
    }
}
