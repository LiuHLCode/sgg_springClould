package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @USER: Liuhl
 * @DATE: 2023/1/19 16:53
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "服务调用失败，提示来自：cloud-consumer-feign-order80";
    }

    @Override
    public String paymentInfo_OUT(Integer id) {
        return null;
    }
}
