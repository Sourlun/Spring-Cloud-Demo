package com.sour.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author xgl
 * @date 2021/1/31 18:15
 **/
public interface IPaymentService {

    /**
     * 正常访问肯定OK
     *
     * @params id
     * @return
     * @author xgl
     * @date 2021/1/31 18:18
     **/
    String paymentInfo_OK(Integer id);

    /**
     * 超时
     *
     * @params id
     * @author xgl
     * @date 2021/1/31 18:18
     **/
    String paymentInfo_TimeOut(Integer id);

    /**
     *  出错
     *
     * @param id
     * @author xgl
     * @date 2021/2/8 17:18
     **/
    String paymentInfo_Error(Integer id);



    /**
     *  服务熔断
     *
     * @author xgl
     * @date 2021/2/12 10:38
     **/
    String paymentCircuitBreaker(@PathVariable("id") Integer id);
}
