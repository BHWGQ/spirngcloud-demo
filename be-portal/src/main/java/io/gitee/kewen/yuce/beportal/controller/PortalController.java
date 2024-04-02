package io.gitee.kewen.yuce.beportal.controller;

import io.gitee.kewen.yuce.beportal.dto.req.RegistReq;
import io.gitee.kewen.yuce.beportal.dto.req.SysLoginReq;
import io.gitee.kewen.yuce.beportal.dto.req.UpdateUserInfoReq;
import io.gitee.kewen.yuce.beportal.dto.resp.RegistResp;
import io.gitee.kewen.yuce.beportal.dto.resp.SysLoginResp;
import io.gitee.kewen.yuce.beportal.dto.resp.UpdateUserInfoResp;
import io.gitee.kewen.yuce.beportal.service.SysLoginService;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelUserInfoResp;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class PortalController {

    @Resource
    private SysLoginService service;

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
    public Result<UpdateUserInfoResp> userInfoRespResult (@RequestBody UpdateUserInfoReq req){
        UpdateUserInfoResp updateUserInfoResp = service.updateUserInfo(req);
        return Result.success(updateUserInfoResp);
    }
}
