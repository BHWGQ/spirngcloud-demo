package io.gitee.kewen.yuce.becomment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

@SpringBootApplication(
        scanBasePackages = {"io.gitee.kewen.yuce.becomment",
                "io.gitee.kewen.yuce.common"}
)
@MapperScan({"io.gitee.kewen.yuce.common.mapper","io.gitee.kewen.yuce.beattractions.mapper"})
@EnableDiscoveryClient
public class BeCommentApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeCommentApplication.class, args);
    }
}
