package springcloud.service.impl;


import com.atguigu.springcloud.entities.Payment;
import org.springframework.stereotype.Service;
import springcloud.dao.PaymentDao;
import springcloud.service.PaymentService;

import javax.annotation.Resource;

/**
 * @USER: Liuhl
 * @DATE: 2022/12/30 15:55
 */
@Service
public class PaymentServiceImpl implements PaymentService {
    //@Resource=@AutoWired+@Qualifier
    @Resource
    private PaymentDao paymentDao;

    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}
