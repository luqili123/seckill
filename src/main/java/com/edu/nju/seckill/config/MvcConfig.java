package com.edu.nju.seckill.config;

import com.edu.nju.seckill.component.JwtInterceptor;
import com.edu.nju.seckill.component.UserArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * WebMvc配置类
 * @author lql
 * @date 2020/1/16 12:23
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /***
     * 自定义拦截器
     */
    @Autowired
    private JwtInterceptor jwtInterceptor;

    /***
     * 自定义参数解析器
     */
    @Autowired
    private UserArgumentResolver userArgumentResolver;


    /***
     * 注册拦截器，设置拦截的请求
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //声明拦截器对象和拦截器请求
        registry.addInterceptor(jwtInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**","/error")
                .excludePathPatterns("/user/login","/user/register"
                        ,"/menu/tabItems","/menu/navItems"
                        ,"/user/chkCode", "/user/verify","/user/password/reset"
                        ,"/goods/**"
                        ,"/seckill/list","/seckill/slide","/seckill/show/{sgid}","/test/redis/{token}", "/seckill/redis");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 新增参数解析器配置
     * @param argumentResolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(userArgumentResolver);

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
}
