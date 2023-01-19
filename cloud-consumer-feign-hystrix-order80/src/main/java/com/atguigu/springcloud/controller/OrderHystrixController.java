package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @USER: Liuhl
 * @DATE: 2023/1/19 14:32
 */
@RestController
@Slf4j
//这个是处理全局服务降级的注解，对应写了@HystrixCommand的方法且没有写@DefaultProperties注解指定具体方法，
//统一用这个指定的
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystrixController {
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OK(id);
        return result;
    }

//    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "1500")
//    })
    @HystrixCommand
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    String paymentInfo_OUT(@PathVariable("id") Integer id){
        String result = paymentHystrixService.paymentInfo_OUT(id);
        return result;
    }
    public String paymentTimeOutFallbackMethod(@PathVariable("id")Integer id){
        return "我是消费者方，对方支付系统繁忙请10秒后重试，或者检查自己设备状态";
    }
    public String payment_Global_FallbackMethod(){
        return "我是全局服务降级";
    }
}
