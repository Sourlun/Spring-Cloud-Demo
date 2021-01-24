package config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign配置
 *
 * @author xgl
 * @date 2021/1/24 19:29
 **/
@Configuration
public class FeignConfig {

    /**
     *  Feign日志
     *      <p> 还需要再yml配置 </p>
     *
     * @author xgl
     * @date 2021/1/24 19:31
     **/
    @Bean
    Logger.Level feignLoggerLevel() {
        // FULL: 代表全面(详细日志)
        return Logger.Level.FULL;
    }

}
