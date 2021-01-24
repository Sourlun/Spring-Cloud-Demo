package com.sour.springcloud.controller;

import com.sour.springcloud.entities.CommonResult;
import com.sour.springcloud.entities.Payment;
import com.sour.springcloud.service.IPaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author xgl
 * @date 2021/1/24 13:53
 **/
@RestController
@Slf4j
public class OrderFeignController {

    @Resource
    private IPaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        CommonResult<Payment> commonResult = paymentFeignService.getPaymentById(id);
        return commonResult;
    }


    /**
     *  模拟feign超时
     *
     * @author xgl
     * @date 2021/1/24 18:05
     **/
    @GetMapping(value = "/consumer/payment/feign/timeout")
    String paymentFeignTimeout() {
        // openFeign-ribbon 客户端一般默认等待1秒钟
        // 超过一秒就报错, 如果想超过一秒则在yml里面配置
        String result = paymentFeignService.paymentFeignTimeout();
        return result;
    }
}
