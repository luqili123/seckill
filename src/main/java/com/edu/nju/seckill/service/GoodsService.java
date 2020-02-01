package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.Goods;
import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.domain.dto.GoodsDetailResult;
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
    * @Description: 获取普通商品列表
    * @Param: [type, orderby, keyword]
    * @return: java.util.List<com.edu.nju.seckill.domain.dto.GoodsListResult>
    * @Author: whn
    * @Date: 2020/2/1
    */
    List<GoodsListResult> getGoodsList(String type,String orderby,String keyword);


    /**
    * @Description: 获取商品详情页
    * @Param: [gid]
    * @return: java.util.List<com.edu.nju.seckill.domain.dto.GoodsDetailResult>
    * @Author: whn
    * @Date: 2020/2/1
    */
    List<GoodsDetailResult> getGoodDetail(long gid);
}
