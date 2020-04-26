package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.SeckillGoodsMapper;
import com.edu.nju.seckill.domain.SeckillGoods;
import com.edu.nju.seckill.domain.dto.SecKillGoodsDetail;
import com.edu.nju.seckill.domain.dto.SeckillGoodsList;
import com.edu.nju.seckill.domain.dto.SeckillGoodsResult;
import com.edu.nju.seckill.exception.DataBaseException;
import com.edu.nju.seckill.exception.GoodsNotFoundException;
import com.edu.nju.seckill.exception.SecKillActivityNotFoundException;
import com.edu.nju.seckill.service.SeckillGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:21
 */
@Service

public class SeckillGoodsServiceImpl implements SeckillGoodsService {

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;

    @Override
    public boolean insertSeckillGoods(SeckillGoods seckillGoods) {
        return seckillGoodsMapper.insertSelective(seckillGoods) != 0;
    }


    @Override
    public SeckillGoods getSeckillBySgid(Long sgid) {
        SeckillGoods seckillGoods = seckillGoodsMapper.selectByPrimaryKey(sgid);
        if (seckillGoods != null) {
            return seckillGoods;
        }
        return null;
    }

    @Override
    public List<SeckillGoodsList> getSeckillList() {
        List<SeckillGoodsList> seckillGoodsLists = seckillGoodsMapper.selectSeckillList();
        if (seckillGoodsLists.size() > 0) {
            return seckillGoodsLists;
        }
        throw new DataBaseException("暂时没有秒杀活动哦");
    }

    @Override
    public SeckillGoodsList getLatestSeckillGoods() {
        //获取结束时间没有超过当前时间的商品
        SeckillGoodsList seckillGoodsList = seckillGoodsMapper.selectLatest();
        if (seckillGoodsList != null) {
            return seckillGoodsList;
        }
        //若当前没有秒杀活动，就返回未来会开始的秒杀
        seckillGoodsList = seckillGoodsMapper.selectFuture();
        if (seckillGoodsList != null) {
            return seckillGoodsList;
        }
        throw new SecKillActivityNotFoundException("近期没有秒杀活动哦＞﹏＜");
    }

    @Override
    public SeckillGoodsList getStartSeckillGoods() {
        //获取结束时间没有超过当前时间的商品
        SeckillGoodsList seckillGoodsList = seckillGoodsMapper.selectLatest();
        if (seckillGoodsList != null) {
            return seckillGoodsList;
        }
        throw new SecKillActivityNotFoundException("近期没有秒杀活动哦＞﹏＜");
    }

    @Override
    public boolean updateSeckillGoodsRemainCount(int remainCount,int sgid) {

        return seckillGoodsMapper.updateRemainCountBySgid(remainCount,sgid)!=0;
    }


    @Override
    public SecKillGoodsDetail getSecKillGoodsById(Long sgid) {
        SecKillGoodsDetail secKillGoodsDetail = seckillGoodsMapper.getSecKillDetail(sgid);
        // TODO 设计缺陷，导致秒杀商品无法收藏，QAQ
        if (secKillGoodsDetail != null)
            return secKillGoodsDetail;
        throw new GoodsNotFoundException("您查询的商品不存在");
    }

}