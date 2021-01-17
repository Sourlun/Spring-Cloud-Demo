package com.sour.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  因为主启动类 @SpringBootApplication 包含@ComponentScan 扫描包的注解,
 *      所以自定义负载均衡不能放在主启动类的包下, 否则会全部consumer都会执行这个负载均衡算法.
 *
 * @author xgl
 * @date 2020/11/15 16:59
 **/
@Configuration
public class MySelfRule {

    /**
     * 自定义负载均衡算法 (默认轮询)
     *  更改后需要在主启动类添加:
     *
     * @author xgl
     * @date 2020/11/15 17:03
     **/
    @Bean
    public IRule myRule() {
        // 随机轮询
        return new RandomRule();
    }

}
