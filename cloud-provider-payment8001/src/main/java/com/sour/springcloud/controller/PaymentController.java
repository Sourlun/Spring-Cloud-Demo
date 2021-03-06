package com.sour.springcloud.controller;

import com.sour.springcloud.entities.CommonResult;
import com.sour.springcloud.entities.Payment;
import com.sour.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 *
 * @author xgl
 * @date 2020/9/13 16:29
 **/
@Controller
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    /**
     *  获取配置文件的: 端口
     */
    @Value("${server.port}")
    private String serverPort;


    /**
     *  服务发现
     *      在启动类加上@EnableDiscoveryClient才可以用
     */
    @Resource
    private DiscoveryClient discoveryClient;


    /**
     *
     *  *注意: 在形参那里如果不加入@RequestBody的话, 会导致接收的数据为空
     *
     * @param payment
     * @return
     */
    @PostMapping(value = "/payment/create")
    @ResponseBody
    public CommonResult create(@RequestBody Payment payment) {

        int result = paymentService.create(payment);
        log.info("****插入结果:" + result);

        if ( result > 0 ) {
            return new CommonResult(200, "插入数据库成功,server.port: " + serverPort, result);
        }else {
            return new CommonResult(444, "插入数据库失败!server.port: " + serverPort, null);
        }
    }


    @GetMapping(value = "/payment/get/{id}")
    @ResponseBody
    public CommonResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果:" + payment);

        if ( payment != null ) {
            return new CommonResult(200, "成功!server.port: " + serverPort, payment);
        }else {
            return new CommonResult(444, "没有!server.port: " + serverPort, null);
        }
    }



    /**
     *  获取服务信息
     *
     * @author xgl
     * @date 2020/9/26 17:02
     **/
    @GetMapping(value = "/payment/discovery")
    @ResponseBody
    public Object discovery() {

        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("******service:" + service);
        }
        /**
         * ******service:cloud-payment-service
         * ******service:cloud-order-service
         */

        // 一个微服务下面的全部实例
        List<ServiceInstance> discoveryClientInstances = discoveryClient.getInstances("cloud-payment-service");
        for (ServiceInstance discoveryClientInstance : discoveryClientInstances) {
            log.info( "***" + discoveryClientInstance.getServiceId()
                    + "\t" + discoveryClientInstance.getHost()
                    + "\t" + discoveryClientInstance.getUri() );
        }
        /**
         *  ***CLOUD-PAYMENT-SERVICE	192.168.248.1	http://192.168.248.1:8001
         *  ***CLOUD-PAYMENT-SERVICE	192.168.248.1	http://192.168.248.1:8002
         */
        return this.discoveryClient;
    }


    /**
     *  模拟feign超时
     *
     * @author xgl
     * @date 2021/1/24 18:05
     **/
    @GetMapping(value = "/payment/feign/timeout")
    @ResponseBody
    public String paymentFeignTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }


}
