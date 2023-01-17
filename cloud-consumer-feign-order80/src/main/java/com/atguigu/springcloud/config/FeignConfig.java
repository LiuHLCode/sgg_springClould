package com.atguigu.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @USER: Liuhl
 * @DATE: 2023/1/17 15:32
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level feignLofferLevel(){
        return Logger.Level.FULL;
    }
}
