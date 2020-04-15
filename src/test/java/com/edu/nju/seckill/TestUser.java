package com.edu.nju.seckill;

import com.edu.nju.seckill.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserService userService;
    @Test
    void test1() {
        System.out.println("expire: " + expire);
    }

    @Test
    void test2() {
        boolean res = userService.hasPhone("15850682877");
        System.out.println("res: " + res);
    }
}
