package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.SeckillGoods;
import org.springframework.stereotype.Repository;

@Repository
public interface SeckillGoodsMapper {
    int deleteByPrimaryKey(Long sgid);

    int insert(SeckillGoods record);

    int insertSelective(SeckillGoods record);

    SeckillGoods selectByPrimaryKey(Long sgid);

    int updateByPrimaryKeySelective(SeckillGoods record);

    int updateByPrimaryKey(SeckillGoods record);
}