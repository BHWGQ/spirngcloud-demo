package io.gitee.kewen.yuce.behotel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.annotation.Order;

@SpringBootApplication(
        scanBasePackages = {"io.gitee.kewen.yuce.behotel",
                "io.gitee.kewen.yuce.common"}
)
@MapperScan({"io.gitee.kewen.yuce.common.mapper"})
@EnableDiscoveryClient
public class BeHotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeHotelApplication.class, args);
    }

}
