package com.edu.nju.seckill.dao;

import com.edu.nju.seckill.domain.SeckillGoods;
import com.edu.nju.seckill.domain.dto.SeckillGoodsList;
import com.edu.nju.seckill.domain.dto.SeckillGoodsResult;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeckillGoodsMapper {

    int deleteByPrimaryKey(Long sgid);

    int insert(SeckillGoods record);

    int insertSelective(SeckillGoods record);

    SeckillGoods selectByPrimaryKey(Long sgid);

    int updateByPrimaryKeySelective(SeckillGoods record);

    int updateByPrimaryKey(SeckillGoods record);

    List<SeckillGoodsList> selectSeckillList();

    SeckillGoodsList selectLatest();

    SeckillGoodsList selectFuture();
}