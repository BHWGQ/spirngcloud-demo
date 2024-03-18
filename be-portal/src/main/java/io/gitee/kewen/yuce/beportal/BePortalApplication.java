package io.gitee.kewen.yuce.beportal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(
        scanBasePackages = {"io.gitee.kewen.yuce.beportal",
                "io.gitee.kewen.yuce.common"}
)
@MapperScan({"io.gitee.kewen.yuce.common.mapper"})
@EnableDiscoveryClient
public class BePortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BePortalApplication.class, args);
    }

}
