package com.sour.springcloud.service;

import com.sour.springcloud.service.impl.PaymentHystrixServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author xgl
 * @date 2021/2/8 15:03
 **/
@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRX-PAYMENT", fallback = PaymentHystrixServiceImpl.class)  // 出错了在哪个方法处理fallback
public interface IPaymentHystrixService {

    /**
     * 正常访问肯定OK
     *  如果: 把提供者关闭了 -> 会执行该方法的fallback (在该类的@FeignClient已经有配置)
     *
     * @author xgl
     * @date 2021年2月8日 15点05分
     **/
    @GetMapping("/payment/hystrix/ok/{id}")
    String paymentInfo_OK(@PathVariable("id") Integer id);


    /**
     * 超时
     *
     * @author xgl
     * @date 2021年2月8日 15点05分
     **/
    @GetMapping("/payment/hystrix/timeOut/{id}")
    String paymentInfo_TimeOut(@PathVariable("id") Integer id);

}
