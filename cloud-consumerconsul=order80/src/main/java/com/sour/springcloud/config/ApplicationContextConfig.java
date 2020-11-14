package com.sour.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author xgl
 * @date 2020/11/14 11:18
 **/
@Configuration
public class ApplicationContextConfig {

    /**
     * RestTemplate 是从 Spring3.0 开始支持的一个 HTTP 请求工具，它提供了常见的REST请求方案的模版，
     * 例如 GET 请求、POST 请求、PUT 请求、DELETE 请求以及一些通用的请求执行方法
     * exchange 以及 execute。RestTemplate 继承自 InterceptingHttpAccessor
     * 并且实现了 RestOperations 接口，其中 RestOperations 接口定义了基本的 RESTful 操作，
     * 这些操作在 RestTemplate 中都得到了实现。
     */
    @Bean
    @LoadBalanced //在使用 RestTemplate 的时候 如果 RestTemplate 上面有 这个注解，那么 这个 RestTemplate 调用的 远程地址，会走负载均衡器。
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
