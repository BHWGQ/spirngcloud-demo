package io.gitee.kewen.yuce.travel.MinioUpload.impl;

import io.gitee.kewen.yuce.common.mapper.TravelPictureTableMapper;
import io.gitee.kewen.yuce.common.mapper.TravelTableMapper;
import io.gitee.kewen.yuce.common.model.dto.req.WriteTravelReq;
import io.gitee.kewen.yuce.common.model.entity.TravelPictureTable;
import io.gitee.kewen.yuce.common.model.entity.TravelTable;
import io.gitee.kewen.yuce.travel.MinioUpload.MinioUploadService;
import io.gitee.kewen.yuce.travel.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
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

    @Resource
    private TravelTableMapper travelTableMapper;

    @Resource
    private TravelPictureTableMapper travelPictureTableMapper;

    @Override
    public Boolean upload(MultipartFile[] files , WriteTravelReq req) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioConfig.getEndpoint())
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                .build();
        LocalDateTime current = LocalDateTime.now();
        MultipartFile file = files[0];
        String fileName = UUID.randomUUID().toString()+ "-" + file.getOriginalFilename();
        minioClient.putObject(PutObjectArgs.builder()
                .bucket(minioConfig.getBucket())
                .object(fileName)
                .stream(file.getInputStream(),file.getSize(),-1)
                .contentType(file.getContentType())
                .build());
        String firstFile = minioConfig.getEndpoint() + "/" +minioConfig.getBucket() + "/" + fileName;
        TravelTable travelTable = TravelTable.builder()
                .userId(req.getUserId())
                .createTime(current)
                .attPicture(firstFile)
                .introduce(req.getIntroduce())
                .recommendPer(req.getRecommendPer())
                .travelDays(req.getTravelDays())
                .travelPays(req.getTravelPays())
                .travelMouth(req.getTravelMouth())
                .attName(req.getAttName())
                .address(req.getAddress())
                .build();
        int affect = travelTableMapper.insert(travelTable);
        if (affect == 0){
            throw new RuntimeException("无法创建该游记");
        }
        int travelId = travelTable.getId();
        for (int i = 1; i < files.length; i++) {
            MultipartFile file1 = files[i];
            String fileName1 = UUID.randomUUID().toString()+ "-" + file1.getOriginalFilename();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .object(fileName1)
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .contentType(file.getContentType())
                    .build());
            String picture = minioConfig.getEndpoint() + "/" +minioConfig.getBucket() + "/" + fileName1;
            TravelPictureTable travelPictureTable = TravelPictureTable.builder()
                    .travelId(travelId)
                    .travelPicture(picture).build();
            int affect1 = travelPictureTableMapper.insert(travelPictureTable);
            if (affect1 == 0){
                throw new RuntimeException("部分图片有点问题哦");
            }
        }
        return true;
    }
}
