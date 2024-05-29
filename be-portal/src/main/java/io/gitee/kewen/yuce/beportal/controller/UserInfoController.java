package io.gitee.kewen.yuce.beportal.controller;

import io.gitee.kewen.yuce.beportal.dto.resp.UserInfoResp;
import io.gitee.kewen.yuce.beportal.service.SysLoginService;
import io.gitee.kewen.yuce.common.bean.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/5/27 15:11
 */
@RestController
@RequestMapping("/userInfo")
@Validated
public class UserInfoController {
    @Resource
    private SysLoginService service;

    @GetMapping("/getUserInfo")
    public Result<List<UserInfoResp>> userInfoQuery (@RequestParam("userName") @NotBlank(message = "请输入用户名") String userName){
        List<UserInfoResp> userInfoResps = service.userInfoQuery(userName);
        return Result.success(userInfoResps);
    }

    @GetMapping("/getUserInfoByUserId")
    public Result<UserInfoResp> userInfoQueryByUserId (@RequestParam("userId") Long userId){
        UserInfoResp userInfoResp = service.userInfoQueryByUserId(userId);
        return Result.success(userInfoResp);
    }
}
