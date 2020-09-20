package com.sour.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 *
 *
 * @author xgl
 * @date 2020/9/20 13:27
 **/
@Configuration
public class ApplicationContextConfig {

    /**
     *  相当于在容器里面放入这个对象
     *
     * @author xgl
     * @date 2020/9/20 13:30
     **/
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
