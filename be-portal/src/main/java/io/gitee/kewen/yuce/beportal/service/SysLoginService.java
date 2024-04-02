package io.gitee.kewen.yuce.beportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.beportal.dto.req.RegistReq;
import io.gitee.kewen.yuce.beportal.dto.req.SysLoginReq;
import io.gitee.kewen.yuce.beportal.dto.req.UpdateUserInfoReq;
import io.gitee.kewen.yuce.beportal.dto.resp.RegistResp;
import io.gitee.kewen.yuce.beportal.dto.resp.SysLoginResp;
import io.gitee.kewen.yuce.beportal.dto.resp.UpdateUserInfoResp;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;

public interface SysLoginService extends IService<LoginTable> {
    SysLoginResp login(SysLoginReq req);

    RegistResp regist(RegistReq req);

    LoginTable getUserById(Long userId);

    UpdateUserInfoResp updateUserInfo(UpdateUserInfoReq req);
}
