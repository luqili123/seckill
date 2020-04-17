package com.edu.nju.seckill.controller;

import com.edu.nju.seckill.common.CommonResult;
import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.domain.dto.GoodsDetailResult;
import com.edu.nju.seckill.domain.dto.GoodsListResult;
import com.edu.nju.seckill.domain.dto.GoodsSearchResult;
import com.edu.nju.seckill.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lql & Kelvin
 * @date 2020/1/11 20:25
 */
@Api(tags = "普通商品控制类")
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 查找获取重点商品轮播图列表
     *
     * @return
     */
    @ApiOperation(value = "获取重点商品轮播图列表", notes = "返回轮播列表list")
    @GetMapping("/carouselItems")
    public CommonResult<List<CarouselItems>> getHotProductCarousel() {
        return CommonResult.success(goodsService.getHotProductCarousel(), "操作成功");
    }

    @ApiOperation(value = "获取普通商品列表按type分类 以orderby排序 以keyword搜索", notes = "返回商品表")
    // /list?type=phone&orderby=default&keyword=xxx
    @GetMapping("/list")
    public CommonResult<List<GoodsListResult>> getGoodsList(@RequestParam("type") String typeName,
                                                            @RequestParam String orderby,
                                                            @RequestParam(required = false) String keyword) {
        List<GoodsListResult> results =goodsService.getGoodsList(typeName, orderby, keyword);
        return CommonResult.success(results);
    }

    @ApiOperation(value = "通过gid获取显示商品详情", notes = "返回轮播列表list")
    @GetMapping("/show/{gid}")
    public CommonResult<List<GoodsDetailResult>> getGoodDetail(@PathVariable long gid) {
        List<GoodsDetailResult> res = goodsService.getGoodDetail(gid);
        if (res.size() > 0)
            return CommonResult.success(res, "操作成功");
        else {
            return CommonResult.failed("无此商品");
        }
    }

    @ApiOperation(value = "商品搜索-获取商城首页商品列表。参数（可选）：keyword")
    @GetMapping({"/list/hot", "/list/hot/{keyword}"})
    public CommonResult<Map<String, List<GoodsSearchResult>>> searchGoodForIndex(@PathVariable(required = false) String keyword) {
        List<GoodsSearchResult> goodsSearchResults = goodsService.searchGoodsForIndex(keyword);
        Map<String, List<GoodsSearchResult>> res = new HashMap<>();
        res.put("list", goodsSearchResults);
        return CommonResult.success(res);
    }

    @ApiOperation(value = "商品搜索-获取首页商品搜索框的搜索提示")
    @GetMapping({"/tips/{keyword}", "/tips"})
    public CommonResult<Map<String, List<String>>> getGoodsIndexTips(@PathVariable(required = false) String keyword) {
        List<String> tips = goodsService.getGoodsIndexTips(keyword);
        Map<String, List<String>> res = new HashMap<>();
        res.put("results", tips);
        return CommonResult.success(res);
    }
}
