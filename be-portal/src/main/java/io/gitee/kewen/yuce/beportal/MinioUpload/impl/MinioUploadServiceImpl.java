package io.gitee.kewen.yuce.beportal.MinioUpload.impl;

import io.gitee.kewen.yuce.beportal.MinioUpload.MinioUploadService;
import io.gitee.kewen.yuce.beportal.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/5 15:33
 */
@Service
public class MinioUploadServiceImpl implements MinioUploadService {

    @Resource
     private MinioConfig minioConfig;

    @Override
    public String upload(MultipartFile file) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioConfig.getEndpoint())
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                .build();
        String fileName = UUID.randomUUID().toString()+ "-" + file.getOriginalFilename();
        minioClient.putObject(PutObjectArgs.builder()
                        .bucket(minioConfig.getBucket())
                        .object(fileName)
                        .stream(file.getInputStream(),file.getSize(),-1)
                        .contentType(file.getContentType())
                        .build());
        return minioConfig.getEndpoint() + "/" +minioConfig.getBucket() + "/" + fileName;
    }

    @Override
    public String deletePicture(String oldPicture) {
        try {
            String newUrl = oldPicture.replace("http://117.72.41.45:9000/user-info/", "");
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(minioConfig.getEndpoint())
                    .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                    .build();
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .object(newUrl)
                    .build());
        }catch (Exception e){
            return e.getMessage();
        }
        return "删除成功";
    }
}
