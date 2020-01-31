package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.Goods;
import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.domain.dto.GoodsListResult;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:19
 */
public interface GoodsService {
    /***
     * 查找获取重点商品轮播图列表
     * @param
     * @return
     */
    List<CarouselItems> getHotProductCarousel();

    Goods getGoodsByGid(long gid);

    /**
    * @Description: 获取商品列表（显示）
    * @Param: []
    * @return: java.util.List<com.edu.nju.seckill.domain.dto.GoodsListResult>
    * @Author: whn
    * @Date: 2020/1/31
    */
    List<GoodsListResult> getGoodsList();

    /**
    * @Description: 获取商品列表（按销量排序）
    * @Param: []
    * @return: java.util.List<com.edu.nju.seckill.domain.dto.GoodsListResult>
    * @Author: whn
    * @Date: 2020/2/1
    */
    List<GoodsListResult> getGoodsListBySales();

    /**
    * @Description: 获取商品列表（按价格排序）
    * @Param: []
    * @return: java.util.List<com.edu.nju.seckill.domain.dto.GoodsListResult>
    * @Author: whn
    * @Date: 2020/2/1
    */
    List<GoodsListResult> getGoodsListByPrice();
}
