package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.AddressMapper;
import com.edu.nju.seckill.dao.GoodsMapper;
import com.edu.nju.seckill.dao.OrderMapper;
import com.edu.nju.seckill.domain.Address;
import com.edu.nju.seckill.domain.Goods;
import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.dto.NavigationResult;
import com.edu.nju.seckill.domain.dto.Order2Param;
import com.edu.nju.seckill.domain.dto.OrderInfoResult;
import com.edu.nju.seckill.domain.dto.OrderParam;
import com.edu.nju.seckill.domain.dto.OrderSearchResult;
import com.edu.nju.seckill.exception.CreateOrderException;
import com.edu.nju.seckill.exception.DataBaseException;
import com.edu.nju.seckill.exception.OrderNotFoundException;
import com.edu.nju.seckill.service.OrderService;
import com.edu.nju.seckill.utils.GuardThread;
import com.edu.nju.seckill.utils.OrderIdUtils;
import com.edu.nju.seckill.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @Autowired
    private RedisUtil redisUtil;

    private Logger logger= LoggerFactory.getLogger(OrderServiceImpl.class);

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
        OrderInfoResult orderInfoResult = orderMapper.selectOrderDetailInfo(uid, oid);
        if (orderInfoResult != null) {
            return orderInfoResult;
        }
        throw new OrderNotFoundException("订单不存在或已被删除");
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

    @Override
    public boolean createSecKillOrder(Long uid, Order order) {
        // 抢夺锁
        String lockVal = UUID.randomUUID().toString();
        String lockKey = "myLock" + order.getGid(); // 秒杀商品的id
        boolean res = redisUtil.setIfAbsent(lockKey, lockVal, 10);
        if (!res)
            throw new CreateOrderException("您没有秒杀到商品");
        // 守护线程，做续命操作
        GuardThread guardThread = new GuardThread(10, lockKey, redisUtil);
        guardThread.setDaemon(true);
        guardThread.start();
        try {
            // TODO 缺少事务
            // 1. 减库存
            boolean res1 = setGoodsCountForRedis("secGood_" + order.getGid());
            // 2. 创建订单
            boolean res2 = createOrderForRedis(OrderIdUtils.getInstance().nextId().toString(), uid, order);
            if (!res1 || !res2)
                throw new CreateOrderException("创建订单失败，请稍后重试");
            return true;
        } finally {
            if (lockVal.equals(redisUtil.get(lockKey))) {
                // 释放锁
                redisUtil.del(lockKey);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertSecOrder(Map<Object, Object> orderInfo) {
        Order order=new Order((String)orderInfo.get("oid"),Long.valueOf((Integer)orderInfo.get("uid"))
                ,Long.valueOf((Integer)orderInfo.get("gid")),(String)orderInfo.get("receiver_phone"),(String)orderInfo.get("receiver_name")
                ,(String)orderInfo.get("address"),(String)orderInfo.get("postcode"),(Integer)orderInfo.get("count")
                ,(Double)orderInfo.get("price"),(Date)orderInfo.get("create_time"),(Date)orderInfo.get("pay_time")
                ,"WeChat",(Date)orderInfo.get("send_time"),(Integer)orderInfo.get("status"),(Integer)orderInfo.get("seckill_flag"));
        if(orderMapper.insertSelective(order)>0){
            return true;
        }

        throw new DataBaseException("秒杀订单写入数据库失败！");
    }

    /**
     * 在redis中插入订单信息
     * @param oid
     * @param uid
     * @param order
     * @return
     */
    private boolean createOrderForRedis(String oid, Long uid, Order order) {
        // 创建订单
        Map<String, Object> secOrder = new HashMap<>();
        secOrder.put("oid", oid);
        secOrder.put("uid", uid);
        secOrder.put("gid", order.getGid());
        secOrder.put("receiver_phone", order.getReceiverPhone());
        secOrder.put("receiver_name", order.getReceiverName());
        secOrder.put("address", order.getAddress());
        secOrder.put("postcode", order.getPostcode());
        secOrder.put("count", order.getCount());
        secOrder.put("price", order.getPrice());
        secOrder.put("create_time", new Date());
        secOrder.put("pay_time", new Date());
        secOrder.put("status", 2);
        secOrder.put("seckill_flag", 2);
        return redisUtil.hmset("secOrder_" + uid + "_" + order.getGid() + "_" + oid, secOrder);
    }

    private boolean setGoodsCountForRedis(String key) {
        int remainCount = getGoodsCountForRedis(key);
        return setGoodsCountForRedis(key, remainCount - 1);
    }

    /**
     * 设置库存
     *
     * @param key 键-secGood-{sgid}
     * @param val 库存值
     * @return true/false
     */
    private boolean setGoodsCountForRedis(String key, int val) {
        return redisUtil.hset(key, "remain_count", val);
    }

    /**
     * 查询秒杀商品的库存
     *
     * @param key 键-secGood_{sgid}
     * @return int
     */
    private int getGoodsCountForRedis(String key) {
        int remainCount = (int) redisUtil.hget(key, "remain_count");
        if (remainCount <= 0)
            throw new CreateOrderException("商品库存不足，无法购买");
        return remainCount;
    }

    private String getStatus(Integer status) {
        String res;
        switch (status) {
            case 1:
                res = "待付款";
                break;
            case 2:
                res = "待发货";
                break;
            case 3:
                res = "待收货";
                break;
            case 4:
                res = "已收货";
                break;
            default:
                res = "";
        }
        return res;
    }

    @Override

    public List<OrderSearchResult> getOrderList(Long uid, Integer status, String keyword) {
        List<OrderSearchResult> results = orderMapper.selectOrderList(uid, status, keyword);
        if (results.size() > 0)
            return results;
        throw new OrderNotFoundException("当前没有" + getStatus(status) + "订单");
    }
}
