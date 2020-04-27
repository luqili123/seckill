package com.edu.nju.seckill;

import com.edu.nju.seckill.service.SeckillGoodsService;
import com.edu.nju.seckill.utils.OrderIdUtils;
import com.edu.nju.seckill.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        int remainCount = (int)redisUtil.hget("secGood_1", "remain_count");
        System.out.println("remainCount: " + remainCount);
        boolean res = redisUtil.hset("secGood_1", "remain_count", remainCount - 1);
        System.out.println("res: " + res);
    }

    @Test
    void test4() {
        Long uid = Long.valueOf((Integer)redisUtil.hget("secOrder_8_4_1254316328409108480", "uid"));
        System.out.println("uid: " + uid);
    }

    @Test
    void test3() {
        // 创建订单
        Map<String, Object> secOrder = new HashMap<>();
        secOrder.put("oid", OrderIdUtils.getInstance().nextId().toString());
        secOrder.put("uid", 8);
        secOrder.put("gid", 1);
        secOrder.put("receiver_phone", "15850682877");
        secOrder.put("receiver_name", "刘文");
        secOrder.put("address", "北京");
        secOrder.put("postcode", "11457");
        secOrder.put("count", 1);
        secOrder.put("price", 25.8);
        secOrder.put("create_time", new Date());
        secOrder.put("pay_time", new Date());
        secOrder.put("status", 2);
        secOrder.put("seckill_flag", 2);
        boolean res = redisUtil.hmset("secOrder_8_1", secOrder);
        System.out.println("res: " + res);
    }

    @Test
    void test2() {
        //redis命令模糊查找
        System.out.print(redisTemplate);
//        Set<String> set= redisTemplate.keys("secGood_"+"*");
//        for (String s : set) {
//            Object sgid=redisUtil.hget(s,"sgid");
//            Object remainCount=redisUtil.hget(s,"remain_count");
//            seckillGoodsService.updateSeckillGoodsRemainCount((int)remainCount,(int)sgid);
//            System.out.println(sgid+" "+remainCount);
//        }
//        redisUtil.del(set);
        Set<String> secOrder=redisUtil.getByPattern("secOrder_"+"*");
        for (String s : secOrder) {
            Map<Object,Object> order=redisUtil.hmget(s);
            System.out.println(order.get("gid")+" "+order.get("pay_time"));
        }
    }
}
