package com.sour.springcloud.service.impl;

import com.sour.springcloud.service.IPaymentHystrixService;
import org.springframework.stereotype.Component;

/**
 *  提供者如果有问题了, 会把实现类的方法默认当成fallback方法
 *      需要在IPaymentHystrixService配置注解: @FeignClient的fallback值
 *      测试方法: 提供者被关闭
 *
 * @author xgl
 * @date 2021/2/9 16:23
 **/
@Component
public class PaymentHystrixServiceImpl implements IPaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-------------- 出错了!  在实现类PaymentHystrixServiceImpl的paymentInfo_OK()执行!";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "-------------- 出错了!  在实现类PaymentHystrixServiceImpl的paymentInfo_TimeOut()执行!";
    }
}
