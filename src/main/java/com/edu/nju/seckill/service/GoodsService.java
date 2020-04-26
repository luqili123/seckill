package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.Goods;
import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.domain.dto.GoodsDetailResult;
import com.edu.nju.seckill.domain.dto.GoodsListResult;
import com.edu.nju.seckill.domain.dto.GoodsSearchResult;
import com.edu.nju.seckill.domain.dto.SecKillGoodsDetail;

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
    List<GoodsListResult> getGoodsList(String typeName, String orderby, String keyword);


    /**
     * @Description: 获取商品详情页
     * @Param: [gid]
     * @return: GoodsDetailResult
     * @Author: whn
     * @Date: 2020/2/1
     */
    GoodsDetailResult getGoodDetail(Long uid, Long gid);


    /**
     * @Description: 首页搜索商品（按分类）
     * @Param: [keyword]
     * @return: java.util.List<com.edu.nju.seckill.domain.dto.GoodsSearchResult>
     * @Author: whn
     * @Date: 2020/2/7
     */
    List<GoodsSearchResult> searchGoodsForIndex(String keyword);

    /**
     * @Description: 商品搜索-获取首页商品搜索框的搜索提示
     * @Param: [keyword]
     * @return: java.util.List<java.lang.String>
     * @Author: whn
     * @Date: 2020/2/8
     */
    List<String> getGoodsIndexTips(String keyword);
}
