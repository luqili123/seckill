package com.edu.nju.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全配置类
 * @author lql
 * @date 2020/1/14 14:46
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //antMatchers("/**").permitAll()，表示所有路径都允许访问
        //anyRequest().authenticated()，所有请求都需要得到授权
        //and().csrf().disable(),使csrf拦截失效，否则啥请求都无法访问
       http.authorizeRequests()
               .antMatchers("/**").permitAll()
               .anyRequest().authenticated()
               .and().csrf().disable();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
