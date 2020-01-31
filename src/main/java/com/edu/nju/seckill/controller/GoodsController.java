package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.domain.dto.GoodsListResult;
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
        return CommonResult.success(goodsService.getHotProductCarousel(),"操作成功");
    }

    @ApiOperation(value="获取普通商品列表",notes = "返回商品表")
    @GetMapping("/goods/list/show")
    public CommonResult<List<GoodsListResult>>getGoodsList(){
        return CommonResult.success(goodsService.getGoodsList(),"操作成功");
    }

    @ApiOperation(value="获取普通商品列表 按销量排序",notes = "返回商品表")
    @GetMapping("/goods/list/count")
    public CommonResult<List<GoodsListResult>>getGoodsListBySales(){
        return CommonResult.success(goodsService.getGoodsListBySales(),"操作成功");
    }

    @ApiOperation(value="获取普通商品列表 按价格排序",notes = "返回商品表")
    @GetMapping("/goods/list/price")
    public CommonResult<List<GoodsListResult>>getGoodsListByPrice(){
        return CommonResult.success(goodsService.getGoodsListByPrice(),"操作成功");
    }
}
