package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.service.SeckillGoodsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author lql
 * @date 2020/1/11 20:26
 */
@Controller
@Api(tags = "秒杀商品控制类")
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;
}
