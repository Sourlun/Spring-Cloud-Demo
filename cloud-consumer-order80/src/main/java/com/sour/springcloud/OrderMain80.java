package com.sour.springcloud;

import com.sour.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 *
 *
 * @author xgl
 * @date 2020/9/20 13:19
 **/
@SpringBootApplication
@EnableEurekaClient
// 自定义轮询算法, name必须和80的controller服务名相同
@RibbonClient( name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
public class OrderMain80 {

    public static void main(String[] args) {
        SpringApplication.run(OrderMain80.class);
    }
}
