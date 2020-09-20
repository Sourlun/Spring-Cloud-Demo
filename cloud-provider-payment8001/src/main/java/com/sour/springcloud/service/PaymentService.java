package com.sour.springcloud.service;

import com.sour.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 *
 *
 * @author xgl
 * @date 2020/9/13 16:24
 **/
public interface PaymentService {

    public int create(Payment payment);


    public Payment getPaymentById(@Param("id") Long id);
}
