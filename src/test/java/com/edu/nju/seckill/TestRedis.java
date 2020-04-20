package com.edu.nju.seckill;

import com.edu.nju.seckill.service.SeckillGoodsService;
import com.edu.nju.seckill.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.Set;

/**
 * @author LiuWen
 * @version 1.0
 * @name TestRedis
 * @description TODO
 * @date 2020-4-15 14:52
 */
@SpringBootTest
public class TestRedis {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    RedisTemplate<String, Object>  redisTemplate;
    @Autowired
    private SeckillGoodsService seckillGoodsService;
    @Test
    void test1() {

    }

    @Test
    void test2() {
        //redis命令模糊查找
        System.out.print(redisTemplate);
        Set<String> set= redisTemplate.keys("secGood_"+"*");
        for (String s : set) {
            Object sgid=redisUtil.hget(s,"sgid");
            Object remainCount=redisUtil.hget(s,"remain_count");
            seckillGoodsService.updateSeckillGoodsRemainCount((int)remainCount,(int)sgid);
            System.out.println(sgid+" "+remainCount);
        }
        redisUtil.del(set);
    }
}
