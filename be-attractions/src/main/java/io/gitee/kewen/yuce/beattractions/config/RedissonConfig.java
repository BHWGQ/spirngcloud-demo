package io.gitee.kewen.yuce.beattractions.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.io.IOException;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/26 13:33
 */
@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redissonClient () throws IOException {
        //1 创建配置
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.182.168:6379")
                .setPassword("20031028");
        //2.根据Config创建出RedissonClient
        return Redisson.create(config);
    }
}
