package io.gitee.kewen.yuce.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wspstart
 * @version 1.0
 * @description
 * @since 2023-12-28 14:27:44
 */
//@SpringBootApplication(
//        scanBasePackages = {"io.gitee.kewen.yuce.gateway", "io.gitee.kewen.propertysales.common.jwt"}
//)
@SpringBootApplication(
        scanBasePackages = {"io.gitee.kewen.yuce.gateway"}
)
@EnableDiscoveryClient
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
