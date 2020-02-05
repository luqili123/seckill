package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author lql
 * @date 2020/1/11 20:25
 */
@Controller
@Api(tags = "订单控制类")
public class OrderController {

    @Autowired
    private OrderService orderService;

    
}
