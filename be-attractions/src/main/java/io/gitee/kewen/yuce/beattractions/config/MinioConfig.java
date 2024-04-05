package io.gitee.kewen.yuce.beattractions.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/5 14:41
 */
@Data
@Configuration
public class MinioConfig {

    @Value(value = "${minio.bucket-name}")
    private String bucket;

    @Value(value = "${minio.endpoint}")
    private String endpoint;

    @Value(value = "${minio.access-key}")
    private String accessKey;

    @Value(value = "${minio.secret-key}")
    private String secretKey;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }
}

