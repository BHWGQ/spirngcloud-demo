package io.gitee.kewen.yuce.beportal.controller;

import io.gitee.kewen.yuce.beportal.dto.req.RegistReq;
import io.gitee.kewen.yuce.beportal.dto.req.SysLoginReq;
import io.gitee.kewen.yuce.beportal.dto.resp.RegistResp;
import io.gitee.kewen.yuce.beportal.dto.resp.SysLoginResp;
import io.gitee.kewen.yuce.beportal.service.SysLoginService;
import io.gitee.kewen.yuce.common.bean.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
