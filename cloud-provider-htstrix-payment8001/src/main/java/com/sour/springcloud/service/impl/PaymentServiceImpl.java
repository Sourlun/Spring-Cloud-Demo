package com.sour.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.sour.springcloud.service.IPaymentService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements IPaymentService {

    /**
     * 正常访问肯定OK
     *
     * @params id
     *
     * @author xgl
     * @date 2021/1/31 18:18
     **/
    @Override
    public String paymentInfo_OK(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_OK, ID:" + id;
    }

    /**
     * 超时
     *
     * @params id
     * @author xgl
     * @date 2021/1/31 18:18
     **/
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHander", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    }) // 报异常了执行的方法
    // (这里规定2秒以内是正常的业务, 超过2秒就走fallbackMethod方法)
    // (需要在主启动类加@EnableCircuitBreaker注解)
    // 会使用hystrix线程单独处理
    public String paymentInfo_TimeOut(Integer id) {
        try {
            // 睡3秒
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOut, ID:" + id
                + "  耗时3秒";
    }

    /**
     *  出错
     *
     * @param id
     * @author xgl
     * @date 2021/2/8 17:18
     **/
    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_ErrorHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    public String paymentInfo_Error(Integer id) {
           int age = 1 / 0;
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOut, ID:" + id
                + "  出错";
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
                + "  断路处理了!     超时啦";
    }


    /**
     * 错误处理
     *
     * @param id
     * @author xgl
     * @date 2021/2/8 17:22
     **/
    public String paymentInfo_ErrorHandler(Integer id) {
        return "线程池: " + Thread.currentThread().getName() + " paymentInfo_TimeOut, ID:" + id
                + "  出错啦!  现在在'错误处理'方法里面处理!";
    }





    /*
        ----------------------------  服务熔断 ------------------------------------------------------------------------------
     */


    /**
     *  服务熔断
     *
     * @author xgl
     * @date 2021/2/12 10:38
     **/
    @Override
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),  //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    //时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),    //失败率达到多少后跳闸
    }) // (在10秒的区间内, 请求10次超过6成失败则跳匝限定)
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id < 0){
            throw new RuntimeException("******id 不能为负数");
        }
        String serialNumber = IdUtil.simpleUUID();  //UUID.randomUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "线程池: " + Thread.currentThread().getName() + "   服务熔断了,   id 不能负数，请稍后再试，o(╥﹏╥)o  id："+id;
    }
}
