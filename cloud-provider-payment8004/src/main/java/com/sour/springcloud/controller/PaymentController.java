package com.sour.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 *
 *
 * @author xgl
 * @date 2020/11/8 18:08
 **/
@RestController
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/zk")
    public String paymentzk() {
        return "spring cloud with zookeeper:" + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
