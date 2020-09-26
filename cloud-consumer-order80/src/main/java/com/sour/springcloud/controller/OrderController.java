package com.sour.springcloud.controller;

import com.sour.springcloud.entities.CommonResult;
import com.sour.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 *
 *
 * @author xgl
 * @date 2020/9/20 13:23
 **/
@RestController
@Slf4j
public class OrderController {

    /**
     *  这种写法是不经过eureka的, 是点对点
     */
//    public static final String PAYMENT_URL = "http://127.0.0.1:8001";


    /**
     * 连接到eureka的微服务
     *  在eureka的管理网页找服务名称
     *  记得在ApplicationContextConfig里面加上 @LoadBalanced // 负载均衡
     *
     *
     */
    public static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";


    @Resource
    private RestTemplate restTemplate;


    /**
     *  新建订单
     *
     * @param payment 订单
     * @return
     * @author xgl
     * @date 2020/9/20 15:33
     **/
    @GetMapping("/consumer/payment/create")
    public CommonResult<Payment> create(Payment payment) {
        log.info("新建"+ payment);
        return restTemplate.postForObject(PAYMENT_URL + "/payment/create", payment, CommonResult.class);
    }


    /**
     * 拿取订单
     *
     * @param id 订单
     * @return
     * @author xgl
     * @date 2020/9/20 15:34
     **/
    @GetMapping("consumer/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        log.info("拿取"+id);
        return restTemplate.getForObject( PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
    }

}
