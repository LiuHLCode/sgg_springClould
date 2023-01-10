package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @USER: Liuhl
 * @DATE: 2023/1/10 16:50
 */
@Configuration
//@Configuration注解的作用：声明一个类为配置类，用于取代bean.xml配置文件注册bean对象。
public class ApplicationContextBean {
    @Bean
    @LoadBalanced
    //负载均衡的注解——赋予RestTemplate负载均衡的能力
    //默认轮询——交替出现
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
