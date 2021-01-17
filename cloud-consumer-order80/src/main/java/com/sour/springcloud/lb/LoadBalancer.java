package com.sour.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 *
 *
 * @author xgl
 * @date 2021/1/17 18:04
 **/
public interface LoadBalancer {

    ServiceInstance serviceInstance(List<ServiceInstance> serviceInstances);
}
