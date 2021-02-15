package com.sour.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sour.springcloud.service.IPaymentHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author xgl
 * @date 2021/2/8 15:07
 **/
@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod") // 配置默认的fallback方法
@Slf4j
public class OrderHystrixController {

    @Resource
    private IPaymentHystrixService paymentHystrixService;

    /**
     * 正常访问肯定OK
     *
     * @author xgl
     * @date 2021年2月8日 15点10分
     **/
    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_OK(id);
        log.info("******result:" + result);
        return result;
    }


    /**
     * 超时
     *  消费者等待时间为1.5秒, 提供者最快也要2秒, 因此会断路处理
     *
     * @author xgl
     * @date 2021年2月8日 15点10分
     **/
    @GetMapping("/consumer/payment/hystrix/timeOut/{id}")
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHander", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    }) // 报异常了执行的方法
    // (这里规定2秒以内是正常的业务, 超过2秒就走fallbackMethod方法)
    // (需要在主启动类加@EnableCircuitBreaker注解)
    // 会使用hystrix线程单独处理
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("******result:" + result);
        return result;
    }


    /**
     * 超时处理
     * @param id
     *
     * @author xgl
     * @date 2021/2/8 17:00
     **/
    public String paymentInfo_TimeOutHander(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOutHander, ID:" + id
                + " 在80消费者端 断路处理了!     超时啦";
    }

    /*
        --------------------------------- 全局fallback方法 START ------------------------------------
     */

    /**
     * 全局fallback
     *  需要在相应的类上面加上@DefaultProperties注解
     *
     * @author xgl
     * @date 2021/2/9 16:11
     **/
    public String payment_Global_FallbackMethod() {
        return "Global异常处理!";
    }



    /**
     * 超时 (使用默认的fallback方法)
     *  消费者等待时间为1.5秒, 提供者最快也要2秒, 因此会断路处理
     *
     * @author xgl
     * @date 2021年2月9日 16点14分
     **/
    @GetMapping("/consumer/payment/hystrix/timeOutGlobal/{id}")
    @HystrixCommand
    public String paymentInfo_TimeOut_Global_Fallback(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        log.info("******result:" + result);
        return result;
    }



    /*
        ================================= 全局fallback方法 END ===========================================
     */

}
