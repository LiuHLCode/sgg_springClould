package com.atguigu.springcloud.controlller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @USER: Liuhl
 * @DATE: 2022/12/30 16:09
 */
@RestController
@Slf4j//日志
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    @Resource
   private EurekaDiscoveryClient eurekaDiscoveryClient;
    @PostMapping(value = "/payment/create")
    public CommonResult create(Payment payment){
        int result = paymentService.create(payment);
        log.info("******插入的结果为："+ result);
        if(result > 0){
            return new CommonResult(200,"数据插入成功"+serverPort,result);
        }else {
            return new CommonResult(444,"插入数据失败",null);
        }
    }
    @GetMapping (value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payment = paymentService.getPaymentById(id);
        log.info("******查询的结果为："+ payment);
        if(payment != null){
            return new CommonResult(200,"查询成功"+serverPort,payment);
        }else {
            return new CommonResult(444,"没有查到id="+id+"对应的记录",null);
        }
    }
    @GetMapping(value ="/payment/discovery")
    public Object discovery(){
        List<String> services = eurekaDiscoveryClient.getServices();
        for (String element : services) {
            log.info("******element"+element);
        }
        List<ServiceInstance> instances = eurekaDiscoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+
                    instance.getPort()+"\t"+instance.getUri());
        }
        return this.eurekaDiscoveryClient;
    }
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
//    模拟opFeign超时
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return serverPort;
    }
}
