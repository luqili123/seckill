package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.dto.CarouselItems;

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
    public List<CarouselItems> getHotProductCarousel();
}
