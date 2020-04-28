package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.GoodsMapper;
import com.edu.nju.seckill.domain.Goods;
import com.edu.nju.seckill.domain.dto.CarouselItems;
import com.edu.nju.seckill.domain.dto.GoodsDetailResult;
import com.edu.nju.seckill.domain.dto.GoodsListResult;
import com.edu.nju.seckill.domain.dto.GoodsSearchResult;
import com.edu.nju.seckill.domain.dto.SecKillGoodsDetail;
import com.edu.nju.seckill.exception.GoodsNotFoundException;
import com.edu.nju.seckill.service.FavoriteService;
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

    @Autowired
    private FavoriteService favoriteService;

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
    public List<GoodsListResult> getGoodsList(String typeName, String orderby, String keyword) {
        List<GoodsListResult> goodsList = goodsMapper.getGoodsList(typeName, orderby, keyword);
        if (goodsList.size() > 0)
            return goodsList;
        throw new GoodsNotFoundException("没找到相关的商品");
    }

    /**
     * 获取商品详情页
     *
     * @param gid
     */
    @Override
    public GoodsDetailResult getGoodDetail(Long uid, Long gid) {
        GoodsDetailResult goodDetail = goodsMapper.getGoodDetail(gid);
        if (goodDetail != null) {
            boolean res = favoriteService.hasFavorite(uid, gid);
            goodDetail.setFavorited(res);
            return goodDetail;
        }
        throw new GoodsNotFoundException("您查询的商品不存在");
    }

    /**
     * 商品分类搜索--首页
     *
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
        return keyword != null ? goodsMapper.getGoodsIndexTips(keyword) : null;
    }

}
