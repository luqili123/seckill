package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.User;
import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.domain.dto.FavoriteResult;
import com.edu.nju.seckill.domain.dto.GoodsDetailResult;
import com.edu.nju.seckill.domain.dto.GoodsListResult;
import com.edu.nju.seckill.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @ApiOperation(value="获取普通商品列表按type分类 以orderby排序 以keyword搜索",notes="返回商品表")
    @GetMapping(value = {"/goods/{type}/list/{orderby}/{keyword}",
                        "/goods/{type}/list/{orderby}"})
    public CommonResult<List<GoodsListResult>> getGoodsListByPrice(
            @PathVariable String type,
            @PathVariable(required = false) String keyword,
            @PathVariable String orderby){
        if(null==keyword)
            keyword="";
        //前端默认值为default
        if("default".equals(orderby))
            orderby="gid";
        //前端传输salescount 但数据库中为count字段
        else if(orderby.equals("salecount"))
            orderby="count";
        List<GoodsListResult> res=goodsService.getGoodsList(type,orderby,keyword);
        if(res.size()>0)
            return CommonResult.success(res,"操作成功");
        else
            return CommonResult.failed("无数据");
    }

    @ApiOperation(value = "通过gid获取显示商品详情",notes = "返回轮播列表list")
    @GetMapping("/goods/show/{gid}")
    public CommonResult<List<GoodsDetailResult>> getGoodDetail(@PathVariable long gid){
        List<GoodsDetailResult> res=goodsService.getGoodDetail(gid);
        if(res.size()>0)
            return CommonResult.success(res,"操作成功");
        else{
            return CommonResult.failed("无此商品");
        }
    }
}
