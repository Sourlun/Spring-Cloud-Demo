package com.sour.springcloud.service;

import com.sour.springcloud.entities.CommonResult;
import com.sour.springcloud.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 *
 * @author xgl
 * @date 2021/1/24 13:44
 **/
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface IPaymentFeignService {

    /**
     *  查询微服务名称"CLOUD-PAYMENT-SERVICE"下面的/payment/get/{id}接口
     *
     * @author xgl
     * @date 2021/1/24 14:04
     **/
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     *  模拟feign超时
     *
     * @author xgl
     * @date 2021/1/24 18:05
     **/
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
