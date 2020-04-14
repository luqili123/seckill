package com.edu.nju.seckill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author LiuWen
 * @version 1.0
 * @name TestUser
 * @description TODO
 * @date 2020-4-15 1:04
 */
@SpringBootTest
public class TestUser {
    @Value("${redis.expire}")
    private long expire;
    @Test
    void test1() {
        System.out.println("expire: " + expire);
    }
}
