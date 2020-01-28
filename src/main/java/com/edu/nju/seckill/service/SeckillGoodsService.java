package com.edu.nju.seckill.service;

import com.edu.nju.seckill.domain.SeckillGoods;
import com.edu.nju.seckill.domain.dto.SeckillGoodsResult;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/11 20:20
 */
public interface SeckillGoodsService {

     boolean insertSeckillGoods(SeckillGoods seckillGoods);

    List<SeckillGoodsResult> getSeckillList();

    SeckillGoods getSeckillBySgid(Long sgid);
}
