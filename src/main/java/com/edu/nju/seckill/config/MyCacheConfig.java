package com.edu.nju.seckill.config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author lql
 * @date 2020/1/7 16:55
 */
@Configuration
public class MyCacheConfig {

    @Bean("myKeyGenerator")
  public KeyGenerator keyGenerator(){
      return new KeyGenerator(){

          @Override
          public Object generate(Object o, Method method, Object... objects) {
              return method.getName()+"["+ Arrays .asList(objects).toString()+"]";
          }
      };
  }
}
