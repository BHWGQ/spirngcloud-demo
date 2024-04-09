package io.gitee.kewen.yuce.travel.controller;

import cn.hutool.core.util.ObjectUtil;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.exception.TravelException.TravelException;
import io.gitee.kewen.yuce.common.feign.PortalClient;
import io.gitee.kewen.yuce.common.model.dto.req.TravelDeleteReq;
import io.gitee.kewen.yuce.common.model.dto.req.WriteTravelReq;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelQueryResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelSingleInfoResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelTenInfoResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelUserInfoResp;
import io.gitee.kewen.yuce.common.model.entity.TravelTable;
import io.gitee.kewen.yuce.common.service.TravelPictureTableService;
import io.gitee.kewen.yuce.common.service.TravelTableService;
import io.gitee.kewen.yuce.travel.MinioUpload.MinioUploadService;
import io.minio.errors.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/travel")
public class TravelQueryController {

    @Resource
    private TravelTableService travelTableService;
    @Resource
    private MinioUploadService minioUploadService;

    @Resource
    private PortalClient portalClient;

    @GetMapping("/tenInfoQuery")
    public Result<List<TravelQueryResp>> travelQueryRespResult (){
        List<TravelTenInfoResp> travelTables = travelTableService.getTenInfo();
        List<TravelQueryResp> travelQueryResps = new ArrayList<>();
        for (TravelTenInfoResp item : travelTables){
            Result<TravelUserInfoResp> travelUserInfoResp = portalClient.result(item.getUserId());
            TravelQueryResp travelQueryResp = new TravelQueryResp(item.getId(),travelUserInfoResp.getData().getUserName(),item.getCreateTime(),item.getAttName(),item.getAttPicture(),travelUserInfoResp.getData().getUserPicture(),item.getAddress(),item.getIntroduce());
            travelQueryResps.add(travelQueryResp);
        }
        return Result.success(travelQueryResps);
    }

    @GetMapping("/{id}")
    public Result<TravelSingleInfoResp> travelSingleInfoRespResult (@PathVariable("id") Integer id){
        TravelTable travelTable = travelTableService.getById(id);
        if (ObjectUtil.isNull(travelTable)){
            throw TravelException.Query_No_Exist;
        }
        Result<TravelUserInfoResp> travelUserInfoRespResult = portalClient.result(travelTable.getUserId());
        TravelSingleInfoResp travelSingleInfoResp = new TravelSingleInfoResp(travelTable,travelUserInfoRespResult.getData().getUserName(),travelUserInfoRespResult.getData().getUserPicture());
        return Result.success(travelSingleInfoResp);
    }

    @PostMapping("/insertTravel")
    public Result<Boolean> result (@RequestPart("files")MultipartFile[] files , WriteTravelReq req) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException{
        Boolean result = minioUploadService.upload(files,req);
        if (!result){
            throw new RuntimeException("无法创建该游记");
        }
        return Result.success(true);
    }

    @GetMapping("/myTravel")
    public Result<List<TravelQueryResp>> userTravel (@RequestParam("userId") Long userId){
        List<TravelTable> travelTables = travelTableService.selectListByUserId(userId);
        Result<TravelUserInfoResp> travelUserInfoRespResult = portalClient.result(userId);
        List<TravelQueryResp> resps = new ArrayList<>();
        for (TravelTable item : travelTables){
            TravelQueryResp travelQueryResp = new TravelQueryResp(item.getId(),travelUserInfoRespResult.getData().getUserName(),item.getCreateTime(),item.getAttName(),item.getAttPicture(),travelUserInfoRespResult.getData().getUserPicture(),item.getAddress(),item.getIntroduce());
            resps.add(travelQueryResp);
        }
        return Result.success(resps);
    }

    //删除游记，删除的同时删除多个表中信息，并且包括minio中的图片
    @PostMapping("/deleteTravel")
    public Result<Boolean> userTravelDelete(@RequestBody List<TravelDeleteReq> reqs) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        Boolean result = minioUploadService.deleteTravel(reqs);
        if (result){
            return Result.success(true);
        }
        return Result.fail(false);
    }

}
