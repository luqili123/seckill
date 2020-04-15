package com.edu.nju.seckill;

import com.edu.nju.seckill.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    @Test
    void test1() {
        boolean res = redisUtil.delete("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJsb2dpbiIsImF1ZCI6ImNsaWVudCIsInJvbGUiOjEsInBob" +
                "25lIjoiMTU4NTA2ODI4NzciLCJuYW1lIjoiOGUyZGU1OTQifQ.rsjsp1RM6HnkCYJhXa3KEH-d4YW5q5k0cjX_tPbsgCA");
        System.out.println("res: " + res);
    }
}
