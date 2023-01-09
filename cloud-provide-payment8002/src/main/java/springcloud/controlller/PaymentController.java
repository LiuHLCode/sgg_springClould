package springcloud.controlller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springcloud.service.PaymentService;

import javax.annotation.Resource;

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
        log.info("******插入的结果为："+ payment);
        if(payment != null){
            return new CommonResult(200,"查询成功"+serverPort,payment);
        }else {
            return new CommonResult(444,"没有查到id="+id+"对应的记录",null);
        }

    }
}
