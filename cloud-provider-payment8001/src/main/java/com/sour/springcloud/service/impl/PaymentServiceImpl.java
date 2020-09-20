package com.sour.springcloud.service.impl;

import com.sour.springcloud.dao.PaymentDao;
import com.sour.springcloud.entities.Payment;
import com.sour.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 *
 * @author xgl
 * @date 2020/9/13 16:25
 **/
@Service
public class PaymentServiceImpl implements PaymentService {

    /**
     *  也可以用@Autowrite, @Resource线程安全
     */
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
