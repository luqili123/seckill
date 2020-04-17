package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.GoodsMapper;
import com.edu.nju.seckill.domain.Goods;
import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.domain.dto.GoodsDetailResult;
import com.edu.nju.seckill.domain.dto.GoodsListResult;
import com.edu.nju.seckill.domain.dto.GoodsSearchResult;
import com.edu.nju.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:21
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    /**
     * 查找获取重点商品轮播图列表
     *
     * @return
     */
    @Override
    public List<CarouselItems> getHotProductCarousel() {
        // TODO 商品首页轮播图的SQL是有逻辑问题的
        return goodsMapper.selectHotProductCarousel();
    }

    @Override
    public Goods getGoodsByGid(long gid) {
        Goods goods = goodsMapper.selectByPrimaryKey(gid);
        if (goods != null) {
            return goods;
        }
        return null;
    }



    /**
     * 获取商品列表
     */
    @Override
    public List<GoodsListResult> getGoodsList(String type, String orderby, String keyword) {
        return goodsMapper.getGoodsList(type,orderby,keyword);
    }

    /**
     * 获取商品详情页
     * @param gid
     */
    @Override
    public List<GoodsDetailResult> getGoodDetail(long gid) {
        return goodsMapper.getGoodDetail(gid);
    }


    /**
     * 商品分类搜索--首页
     * @param keyword
     * @return
     */
    @Override
    public List<GoodsSearchResult> searchGoodsForIndex(String keyword) {
        return goodsMapper.searchGoodsForIndex(keyword);
    }

    /**
    * @Description: 商品搜索-获取首页商品搜索框的搜索提示
    * @Param: [keyword]
    * @return: java.util.List<java.lang.String>
    * @Author: whn
    * @Date: 2020/2/8
    */
    @Override
    public List<String> getGoodsIndexTips(String keyword) {
        if (keyword != null)
            return goodsMapper.getGoodsIndexTips(keyword);
        return null;
    }
}
