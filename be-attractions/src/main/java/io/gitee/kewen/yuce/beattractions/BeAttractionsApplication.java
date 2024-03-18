package io.gitee.kewen.yuce.beattractions;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

@SpringBootApplication(
        scanBasePackages = {"io.gitee.kewen.yuce.beattractions",
                "io.gitee.kewen.yuce.common"}
)
@MapperScan({"io.gitee.kewen.yuce.common.mapper","io.gitee.kewen.yuce.beattractions.mapper"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"io.gitee.kewen.yuce.common.feign"})
public class BeAttractionsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeAttractionsApplication.class, args);
    }
}
