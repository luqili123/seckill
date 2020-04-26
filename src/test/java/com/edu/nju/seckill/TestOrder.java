package com.edu.nju.seckill;

import com.edu.nju.seckill.dao.OrderMapper;
import com.edu.nju.seckill.domain.Order;
import com.edu.nju.seckill.domain.dto.Order2Param;
import com.edu.nju.seckill.service.OrderService;
import com.edu.nju.seckill.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author LiuWen
 * @version 1.0
 * @name TestOrder
 * @description TODO
 * @date 2020-4-23 1:31
 */
@SpringBootTest
public class TestOrder {
    @Autowired
    OrderService orderService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    private OrderMapper orderMapper;
    @Test
    void test01() {
        Long uid = 8L;
        Order2Param order2Param = new Order2Param();
        order2Param.setAddressId(18);
        order2Param.setGoodsId(37L);
        order2Param.setNum(3);
        order2Param.setPayWay("weixin");
        boolean res = orderService.createOrder(uid, order2Param);
        System.out.println("res: " + res);
    }
    @Test
    void test(){
        Set<String> secOrder=redisUtil.getByPattern("secOrder"+"*");
        //更新数据
        if(secOrder!=null){
            for (String s : secOrder) {
                Map<Object,Object> orderInfo=redisUtil.hmget(s);
//                Long uid=Long.parseLong(orderInfo.get("uid").toString());
//                System.out.println(orderInfo.toString());
                Order order=new Order((String)orderInfo.get("oid"),Long.parseLong(orderInfo.get("uid").toString())
                        ,Long.parseLong(orderInfo.get("gid").toString()),(String)orderInfo.get("receiver_phone"),(String)orderInfo.get("receiver_name")
                        ,(String)orderInfo.get("address"),(String)orderInfo.get("postcode"),(Integer)orderInfo.get("count")
                        ,(Double)orderInfo.get("price"),(Date)orderInfo.get("create_time"),(Date)orderInfo.get("pay_time")
                        ,"WeChat",(Date)orderInfo.get("send_time"),(Integer)orderInfo.get("status"),(Integer)orderInfo.get("seckill_flag"));
                orderMapper.insertSelective(order);
                System.out.println(order);
            }

        }

    }

}
