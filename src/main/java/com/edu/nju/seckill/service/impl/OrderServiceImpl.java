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
    * @Description: 搜索用户订单 通过可选参数keyword进行搜索
    * @Param: [uid, keyword]
    * @return: java.util.List<com.edu.nju.seckill.domain.dto.OrderSearchResult>
    * @Author: whn
    * @Date: 2020/2/7
    */
    @Override
    public List<OrderSearchResult> searchOrder(Long uid, String keyword) {
        return orderMapper.searchOrder(uid,keyword);
    }

    @Override
    public boolean createOrder(Order order) {
        return orderMapper.insert(order)==1;
    }
}
