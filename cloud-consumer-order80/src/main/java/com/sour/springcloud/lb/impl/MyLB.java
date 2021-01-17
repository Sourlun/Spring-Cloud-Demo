package com.sour.springcloud.lb.impl;

import com.sour.springcloud.lb.LoadBalancer;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            // 2147483647 int最大的数字
            next = current >= 2147483647 ? 0 : current + 1;
        } while ( !this.atomicInteger.compareAndSet(current, next) );
        System.out.println("----第几次, 访问次数:next:" + next);
        return next;
    }


    @Override
    public ServiceInstance serviceInstance(List<ServiceInstance> serviceInstances) {
        // 获取当前服务坐标
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
