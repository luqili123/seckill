package com.edu.nju.seckill.config;

import com.edu.nju.seckill.component.JwtInterceptor;
import com.edu.nju.seckill.component.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author lql
 * @date 2020/1/16 12:23
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Autowired
    private JwtInterceptor jwtInterceptor;
    /***
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //声明拦截器对象和拦截器请求
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/error")
                .excludePathPatterns("/users/login","/users/signUp");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 1/23新增参数解析器配置
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(UserArgumentResolver());

    }
    private UserArgumentResolver UserArgumentResolver() {
        UserArgumentResolver userArgumentResolver = new UserArgumentResolver();
        return userArgumentResolver;
    }
}
