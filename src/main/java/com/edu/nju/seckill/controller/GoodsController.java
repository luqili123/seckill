package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:25
 */
@RestController
@Api(tags = "普通商品控制类")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查找获取重点商品轮播图列表
     * @return
     */
    @ApiOperation(value = "获取重点商品轮播图列表",notes = "返回轮播列表list")
    @GetMapping("/goods/carouselItems")
    public CommonResult<List<CarouselItems>> getHotProductCarousel(){
        return CommonResult.success(goodsService.getHotProductCarousel(),"传递成功");
    }
}
