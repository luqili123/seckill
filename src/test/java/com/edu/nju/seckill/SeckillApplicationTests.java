package com.edu.nju.seckill;

import com.alibaba.fastjson.JSON;
import com.edu.nju.seckill.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class SeckillApplicationTests {
    @Resource
    RedisUtil redisUtil;

    @Test
    void contextLoads() {
        System.out.println(redisUtil.get("msg"));

    }




}
