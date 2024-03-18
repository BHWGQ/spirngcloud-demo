package io.gitee.kewen.yuce.beweather;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
        scanBasePackages = {"io.gitee.kewen.yuce.beweather",
                "io.gitee.kewen.yuce.common"}
)
@MapperScan({"io.gitee.kewen.yuce.common.mapper"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"io.gitee.kewen.yuce.common.feign"})
public class BeWeatherApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeWeatherApplication.class, args);
    }
}
