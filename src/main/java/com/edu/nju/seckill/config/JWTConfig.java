package com.edu.nju.seckill.config;

import com.edu.nju.seckill.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * jwt配置类
 * @author lql
 * @date 2020/1/15 16:52
 */
@Configuration
public class JWTConfig {

    /***
     * 将jwt工具类注入容器
     * @return
     */
    @Bean
    public JwtUtil jwtUtil(){
        return new JwtUtil();
    }
}
