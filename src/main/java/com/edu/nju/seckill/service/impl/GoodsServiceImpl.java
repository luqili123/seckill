package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.GoodsMapper;
import com.edu.nju.seckill.domain.Goods;
import com.edu.nju.seckill.domain.dto.CarouselItems;
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
     * @return
     */
    @Override
    public List<CarouselItems> getHotProductCarousel() {
        return goodsMapper.selectHotProductCarousel();
//        return null;
    }

    @Override
    public boolean hasGoods(long gid) {

        return goodsMapper.selectByPrimaryKey(gid)!=null;
    }

    @Override
    public Goods getGoodsByGid(long gid) {
        Goods goods=goodsMapper.selectByPrimaryKey(gid);
        if(goods!=null){
            return goods;
        }
        return null;
    }

}
