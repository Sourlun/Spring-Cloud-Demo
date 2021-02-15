package com.sour.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sour.springcloud.service.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author xgl
 * @date 2021/1/31 18:24
 **/
@RestController
@Slf4j
public class PaymentController {

    @Resource
    private IPaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;


    /**
     * 正常访问肯定OK
     *
     * @author xgl
     * @date 2021/1/31 18:28
     **/
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_OK(id);
        log.info("******result:" + result);
        return result;
    }


    /**
     * 超时
     *
     * @author xgl
     * @date 2021/1/31 18:28
     **/
    @GetMapping("/payment/hystrix/timeOut/{id}")
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_TimeOut(id);
        log.info("******result:" + result);
        return result;
    }

    /**
     * 出错
     *
     * @author xgl
     * @date 2021年2月8日 17点20分
     **/
    @GetMapping("/payment/hystrix/error/{id}")
    public String paymentInfo_Error(@PathVariable("id") Integer id) {
        String result = paymentService.paymentInfo_Error(id);
        log.info("******result:" + result);
        return result;
    }


    /*
        --------------------- 服务熔断 --------------------------
     */


    /**
     *  服务熔断
     *
     * @author xgl
     * @date 2021/2/12 10:38
     **/
    @GetMapping("/payment/hystrix/paymentCircuitBreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("******result:" + result);
        return result;
    }
}
