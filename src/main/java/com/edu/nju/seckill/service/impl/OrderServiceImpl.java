package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.AddressMapper;
import com.edu.nju.seckill.dao.GoodsMapper;
import com.edu.nju.seckill.dao.OrderMapper;
import com.edu.nju.seckill.domain.Address;
import com.edu.nju.seckill.domain.Goods;
import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.dto.Order2Param;
import com.edu.nju.seckill.domain.dto.OrderInfoResult;
import com.edu.nju.seckill.domain.dto.OrderSearchResult;
import com.edu.nju.seckill.exception.CreateOrderException;
import com.edu.nju.seckill.service.OrderService;
import com.edu.nju.seckill.utils.OrderIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:21
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private AddressMapper addressMapper;

    /**
     * @Description: 通过状态以及关键字搜索订单
     * @Param: [uid, status, keyword]
     * @return: java.util.List<com.edu.nju.seckill.domain.dto.OrderSearchResult>
     * @Author: whn
     * @Date: 2020/2/12
     */
    @Override
    public List<OrderSearchResult> searchOrder(Long uid, int status, String keyword) {
        if (status == 5)
            //返回所有数据
            return orderMapper.searchOrder(uid, status, keyword);
        else
            //通过status筛选
            return orderMapper.searchOrderViaStatus(uid, status, keyword);
    }


    @Override
    public boolean createOrder(Order order) {
        return orderMapper.insert(order) == 1;
    }

    @Override
    public boolean deleteByOid(String oid) {
        return orderMapper.deleteByPrimaryKey(oid) == 1;
    }

    @Override
    public OrderInfoResult getOrderInfo(Long uid, String oid) {
        OrderInfoResult order = orderMapper.selectOrderDetailInfo(uid, oid);
        if (order != null) {
            return order;
        }
        return null;
    }

    @Override
    public List<Order> getOrderByStatus(int status) {
        List<Order> orders = orderMapper.selectByStatus(status);
        if (orders != null && orders.size() > 0) {
            return orders;
        }
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createOrder(Long uid, Order2Param order2Param) {
        // 1. 减库存
        if (goodsMapper.updateGoodsCount(order2Param.getGoodsId(), order2Param.getNum()) == 1) {
            // 2.1 查询商品信息
            Goods goods = goodsMapper.selectByPrimaryKey(order2Param.getGoodsId());
            // 2.2 查询收货地址
            Address address = addressMapper.selectByPrimaryKey(order2Param.getAddressId());
            Order order = new Order(OrderIdUtils.getInstance().nextId().toString(), uid, order2Param.getNum(),
                    order2Param.getPayWay(), goods, address, 1);
            // 2. 创建订单
            if (orderMapper.insert(order) == 1)
                return true;
            throw new CreateOrderException("创建订单失败，请稍后重试");
        }
        throw new CreateOrderException("商品库存不足，无法购买");
    }
}
