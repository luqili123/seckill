package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.service.SeckillGoodsService;
import io.swagger.annotations.Api;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author lql
 * @date 2020/1/11 20:26
 */
@Controller
@Api(tags = "秒杀商品控制类")
public class SeckillGoodsController {

    @Autowired
    private SeckillGoodsService seckillGoodsService;

//    @GetMapping("/seckill/slide")
//    public CommonResult<?>  getSecGoodsItems(){
//        return null;
//    }

//    @PostMapping("/seckill")
//    public CommonResult<?> addSeckillGoods(){
//
//    }
}
