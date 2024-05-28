package io.gitee.kewen.yuce.beportal.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.beportal.dto.req.RegistReq;
import io.gitee.kewen.yuce.beportal.dto.req.SysLoginReq;
import io.gitee.kewen.yuce.beportal.dto.req.UpdateUserInfoReq;
import io.gitee.kewen.yuce.beportal.dto.resp.RegistResp;
import io.gitee.kewen.yuce.beportal.dto.resp.SysLoginResp;
import io.gitee.kewen.yuce.beportal.dto.resp.SysLoginUpdateResp;
import io.gitee.kewen.yuce.beportal.dto.resp.UserInfoResp;
import io.gitee.kewen.yuce.common.model.dto.resp.UserSubscribeResp;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;

import java.util.List;

public interface SysLoginService extends IService<LoginTable> {
    SysLoginResp login(SysLoginReq req);

    RegistResp regist(RegistReq req);

    LoginTable getUserById(Long userId);

    SysLoginUpdateResp updateUserInfo(UpdateUserInfoReq req);

    Boolean updateUserPicture(Integer userId, String oldPicture, String pictureUrl);

    String getByEmail(Long userId);

    String getUserPictureByUserId(Long userId);

    List<UserInfoResp> userInfoQuery(String userName);

    UserSubscribeResp getUserSub(Long userId);
}
