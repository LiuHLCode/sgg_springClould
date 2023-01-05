package com.atguigu.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @USER: Liuhl
 * @DATE: 2023/1/5 10:51
 */
//@Configuration注解的作用：声明一个类为配置类，用于取代bean.xml配置文件注册bean对象。
@Configuration
public class ApplicationContextConfig {
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

}
