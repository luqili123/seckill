package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.SeckillGoodsMapper;
import com.edu.nju.seckill.domain.SeckillGoods;
import com.edu.nju.seckill.domain.dto.SeckillGoodsList;
import com.edu.nju.seckill.domain.dto.SeckillGoodsResult;
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
        return seckillGoodsMapper.insertSelective(seckillGoods)!=0;
    }


    @Override
    public SeckillGoods getSeckillBySgid(Long sgid) {
        SeckillGoods seckillGoods=seckillGoodsMapper.selectByPrimaryKey(sgid);
        if(seckillGoods!=null){
            return seckillGoods;
        }
        return null;
    }

    @Override
    public List<SeckillGoodsList> getSeckillList() {
        List<SeckillGoodsList> seckillGoodsLists=seckillGoodsMapper.selectSeckillList();
        if(seckillGoodsLists!=null&&seckillGoodsLists.size()>0){
            return seckillGoodsLists;
        }
        return null;
    }
}
