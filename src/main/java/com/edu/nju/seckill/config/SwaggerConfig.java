package com.edu.nju.seckill.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Swagger配置类
 * @author lql
 * @date 2020/1/10 15:22
 * @EnableSwagger2：开启Swagger2
 */
@Configuration
public class SwaggerConfig {


    /**
     *
     *  配置Swagger2的Docket的bean示例
     *
     *  .groupName("SecKill"):设置分组名称
     *  RequestHandlerSelectors,配置要扫描的接口;basePackage指定要扫描的包；
     *  withClassAnnotation：扫描类上的注解例如withClassAnnotation(RestController.class)
     *  withMethodAnnotation：扫描方法上的注解例如withClassAnnotation(GetMapping.class)
     *  .paths（“路径”） ：过滤什么路径 例如：apis().paths(PathSelectors.ant(""))
     *  enable(true):是否启用Swagger，false则浏览器无法访问
     *  我只希望Swagger在生产环境中使用，在发布时不使用？
     *      1.判断是否是生产环境
     *      2.注入enable()
     *
     *
     * @return
     * @author lql
     * @date 2020/1/10 15:35
     */
    @Bean
    public Docket docket(Environment environment){

        //设置显示Swagger的环境
        Profiles profiles=Profiles.of("dev","test");

        //获取项目环境:通过该方法判断是否处在设置的环境当中
        boolean flag=environment.acceptsProfiles(profiles);


        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("SecKill")
                .enable(flag)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.edu.nju.seckill.controller"))
                .build();

    }

    /***
     * 配置多个分组，需要创建多个docket实例
     * @return
     */
    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("hh");
    }

    /** 
     *
     * 配置swagger的相关信息
     * @return   
     * @author lql
     * @date 2020/1/10 15:36 
     */
    private ApiInfo apiInfo(){
        //name,url,email
        Contact contact=new Contact("lql","https://blog.csdn.net/Conqueror_","1036110216@qq.com");

        //title,description,version,termsOfServiceUrl,DEFAULT_CONTACT,license,licenseUrl,Arraylist

        return new ApiInfo("SecKill API",
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+
                        "今天又是努力学习的一天哦！","v1.0",
                "49.235.238.192:8888",contact,"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0",

                new ArrayList());
    }

}
