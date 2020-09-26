package com.sour.springcloud.controller;

import com.sour.springcloud.entities.CommonResult;
import com.sour.springcloud.entities.Payment;
import com.sour.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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


}
