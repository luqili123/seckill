package com.edu.nju.seckill;

import com.edu.nju.seckill.domain.dto.Order2Param;
import com.edu.nju.seckill.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
