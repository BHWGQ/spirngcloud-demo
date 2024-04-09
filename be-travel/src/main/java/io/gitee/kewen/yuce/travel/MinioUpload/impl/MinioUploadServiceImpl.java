package io.gitee.kewen.yuce.travel.MinioUpload.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.gitee.kewen.yuce.common.exception.TravelException.TravelException;
import io.gitee.kewen.yuce.common.mapper.*;
import io.gitee.kewen.yuce.common.model.dto.req.TravelDeleteReq;
import io.gitee.kewen.yuce.common.model.dto.req.WriteTravelReq;
import io.gitee.kewen.yuce.common.model.entity.*;
import io.gitee.kewen.yuce.travel.MinioUpload.MinioUploadService;
import io.gitee.kewen.yuce.travel.config.MinioConfig;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
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

    @Resource
    private TravelCommentMapper travelCommentMapper;

    @Resource
    private CollectTravelTableMapper collectTravelTableMapper;

    @Resource
    private HobbyTravelTableMapper hobbyTravelTableMapper;

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

    //先删除关于游记的所有minio图片信息，然后再去删除两个表中的数据。最后，删除关于游记评论以及收藏以及喜欢游记表的信息
    @Transactional
    @Override
    public Boolean deleteTravel(List<TravelDeleteReq> reqs) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioConfig.getEndpoint())
                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
                .build();
        for (TravelDeleteReq item : reqs){
            LambdaQueryWrapper<TravelTable> wrapper = new QueryWrapper<TravelTable>().lambda()
                    .eq(TravelTable::getId,item.getId())
                    .eq(TravelTable::getUserId,item.getUserId());
            TravelTable travelTable = travelTableMapper.selectOne(wrapper);
            if (ObjectUtil.isNull(travelTable)){
                throw TravelException.Delete_Have_Error;
            }
            String newUrl = travelTable.getAttPicture().replace("http://117.72.41.45:9000/travel-plan/", "");
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .object(newUrl)
                    .build());
            travelTableMapper.delete(wrapper);
            LambdaQueryWrapper<TravelPictureTable> lambdaQueryWrapper = new QueryWrapper<TravelPictureTable>().lambda()
                    .eq(TravelPictureTable::getTravelId,travelTable.getId());
            List<TravelPictureTable> travelPictureTables = travelPictureTableMapper.selectList(lambdaQueryWrapper);
            if (CollectionUtil.isNotEmpty(travelPictureTables)){
                for (TravelPictureTable t1 : travelPictureTables){
                    String deleteUrlPicture = t1.getTravelPicture().replace("http://117.72.41.45:9000/travel-plan/", "");
                    minioClient.removeObject(RemoveObjectArgs.builder()
                            .bucket(minioConfig.getBucket())
                            .object(deleteUrlPicture)
                            .build());
                    travelPictureTableMapper.deleteById(item.getId());
                }
            }
            LambdaQueryWrapper<TravelComment> travelCommentLambdaQueryWrapper = new QueryWrapper<TravelComment>().lambda()
                    .eq(TravelComment::getTravelId,travelTable.getId());
            List<TravelComment> travelComments = travelCommentMapper.selectList(travelCommentLambdaQueryWrapper);
            if (CollectionUtil.isNotEmpty(travelComments)){
                for (TravelComment comment : travelComments){
                    travelCommentMapper.deleteById(comment.getId());
                }
            }
            LambdaQueryWrapper<CollectTravelTable> collectTravelTableLambdaQueryWrapper = new QueryWrapper<CollectTravelTable>().lambda()
                    .eq(CollectTravelTable::getTravelId,travelTable.getId())
                    .eq(CollectTravelTable::getUserId,travelTable.getUserId());
            List<CollectTravelTable> list = collectTravelTableMapper.selectList(collectTravelTableLambdaQueryWrapper);
            if (CollectionUtil.isNotEmpty(list)){
                for (CollectTravelTable collectTravelTable : list){
                    collectTravelTableMapper.deleteById(collectTravelTable.getId());
                }
            }
            LambdaQueryWrapper<HobbyTravelTable> hobbyTravelTableLambdaQueryWrapper = new QueryWrapper<HobbyTravelTable>().lambda()
                    .eq(HobbyTravelTable::getTravelId,travelTable.getId())
                    .eq(HobbyTravelTable::getUserId,travelTable.getUserId());
            List<HobbyTravelTable> hobbyTravelTables = hobbyTravelTableMapper.selectList(hobbyTravelTableLambdaQueryWrapper);
            if (CollectionUtil.isNotEmpty(hobbyTravelTables)){
                for (HobbyTravelTable hobbyTravelTable: hobbyTravelTables){
                    hobbyTravelTableMapper.deleteById(hobbyTravelTable.getId());
                }
            }
        }
        return true;
    }


}
