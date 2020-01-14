package com.edu.nju.seckill;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author lqllq
 */
@SpringBootApplication
//@EnableCaching
@MapperScan("com.edu.nju.seckill.dao")
@EnableSwagger2
public class SeckillApplication {

    public static void main(String[] args) {

        SpringApplication.run(SeckillApplication.class, args);
        //test
    }

}
