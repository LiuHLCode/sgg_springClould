package com.itguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @USER: Liuhl
 * @DATE: 2023/1/11 15:40
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/payment/consul")
    public String paymentInfo(){
        return "springCloud with consul: " +serverPort + "\t\t"+ UUID.randomUUID().toString();
    }
}
