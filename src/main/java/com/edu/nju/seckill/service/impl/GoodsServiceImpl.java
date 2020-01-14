package com.edu.nju.seckill.service.impl;

import com.edu.nju.seckill.dao.GoodsMapper;
import com.edu.nju.seckill.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lql
 * @date 2020/1/11 20:21
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;
}
