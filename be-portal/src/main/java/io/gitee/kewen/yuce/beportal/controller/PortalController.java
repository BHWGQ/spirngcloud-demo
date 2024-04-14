package io.gitee.kewen.yuce.beportal.controller;

import io.gitee.kewen.yuce.beportal.dto.req.RegistReq;
import io.gitee.kewen.yuce.beportal.dto.req.SysLoginReq;
import io.gitee.kewen.yuce.beportal.dto.req.UpdatePictureReq;
import io.gitee.kewen.yuce.beportal.dto.req.UpdateUserInfoReq;
import io.gitee.kewen.yuce.beportal.dto.resp.*;
import io.gitee.kewen.yuce.beportal.service.SysLoginService;
import io.gitee.kewen.yuce.beportal.MinioUpload.MinioUploadService;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelUserInfoResp;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;
import io.minio.errors.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class PortalController {

    @Resource
    private SysLoginService service;

    @Resource
    private MinioUploadService minioUploadService;

    @PostMapping("/login")
    public Result<SysLoginResp> login (@RequestBody @Valid SysLoginReq req){
        SysLoginResp sysLoginResp = service.login(req);
        return Result.success(sysLoginResp);
    }

    @PostMapping("/regist")
    public Result<RegistResp> regist (@RequestBody @Valid RegistReq req){
        RegistResp registResp = service.regist(req);
        return Result.success(registResp);
    }

    @GetMapping("/userInfo")
    public Result<TravelUserInfoResp> result (@RequestParam("userId") Long userId){
        LoginTable loginTable = service.getUserById(userId);
        TravelUserInfoResp travelUserInfoResp = new TravelUserInfoResp(loginTable.getUserName(),loginTable.getPicture());
        return Result.success(travelUserInfoResp);
    }

    @PostMapping("/updateUserInfo")
    public Result<SysLoginUpdateResp> userInfoRespResult (@RequestBody UpdateUserInfoReq req){
        SysLoginUpdateResp updateUserInfoResp = service.updateUserInfo(req);
        return Result.success(updateUserInfoResp);
    }

    @PostMapping("/updatePicture")
    public Result<UpdateUserPicture> updateUserPictureResult (@RequestParam("file") MultipartFile file , @RequestParam("userId") Integer userId, @RequestParam("oldPicture") String oldPicture) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        if (file.isEmpty()) {
            throw new RuntimeException("上传失败，收到的文件为空");
        }
        String pictureUrl = minioUploadService.upload(file);
        Boolean updateUserInfo = service.updateUserPicture(userId,oldPicture,pictureUrl);
        if (!updateUserInfo){
            return Result.fail(null);
        }
        String deleteUrl = minioUploadService.deletePicture(oldPicture);
        if (!"删除成功".equals(deleteUrl)){
            return Result.fail(null);
        }
        return Result.success(new UpdateUserPicture(pictureUrl));
    }

    @GetMapping("/getEmailByUserId")
    public Result<String> getEmail (@RequestParam("userId") Long userId){
        String email = service.getByEmail(userId);
        return Result.success(email);
    }

}
