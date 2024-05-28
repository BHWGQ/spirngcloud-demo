package io.gitee.kewen.yuce.common.feign;

import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelUserInfoResp;
import io.gitee.kewen.yuce.common.model.dto.resp.UserSubscribeResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "portal-service",path = "/portal/user")
public interface PortalClient {
    @GetMapping("/userInfo")
    Result<TravelUserInfoResp> result (@RequestParam("userId") Long userId);

    @GetMapping("/getEmailByUserId")
    Result<String> getEmail (@RequestParam("userId") Long userId);

    @GetMapping("/getUserPicture")
    Result<String> getUserPicture (@RequestParam("userId") Long userId);

    @GetMapping("/getUserSub")
    Result<UserSubscribeResp> userSubscribeRespResult (@RequestParam("userId") Long userId);
}
