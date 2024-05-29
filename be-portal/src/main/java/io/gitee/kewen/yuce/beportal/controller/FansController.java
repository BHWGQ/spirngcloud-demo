package io.gitee.kewen.yuce.beportal.controller;

import cn.hutool.core.collection.CollectionUtil;
import io.gitee.kewen.yuce.beportal.service.SysLoginService;
import io.gitee.kewen.yuce.common.feign.PortalClient;
import io.gitee.kewen.yuce.common.model.dto.req.UserSubscribeInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.UserSubscribeResp;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.entity.FansTable;
import io.gitee.kewen.yuce.common.service.FansTableService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/5/28 8:52
 */
@RestController
@RequestMapping("/manageFans")
public class FansController {
    @Resource
    private FansTableService service;

    @Resource
    private SysLoginService sysLoginService;

    //查询某个用户的粉丝量
    @GetMapping("/getFansCount")
    public Result<Integer> queryUserFansCount(@RequestParam("userId") Long userId){
        int fansCounts = service.getUserFansCounts(userId);
        return Result.success(fansCounts);
    }

    //查询某个用户的关注量
    @GetMapping("/getSubscribeCount")
    public Result<Integer> queryUserSubscribeCount(@RequestParam("userId") Long userId){
        int fansCounts = service.getUserSubscribeCounts(userId);
        return Result.success(fansCounts);
    }

    //查询某个用户关注的列表对象
    //返回用户头像，账号，名称，个性签名
    @GetMapping("/getUserSubscribe")
    public Result<List<UserSubscribeResp>> userSubscribeRespResult (@RequestParam("userId") Long userId){
        List<FansTable> fansTables = service.userSubscribeQuery(userId);
        if (CollectionUtil.isEmpty(fansTables)){
            return Result.success(null);
        }
        List<UserSubscribeResp> userSubscribeResps = new ArrayList<>();
        for (FansTable item : fansTables){
            UserSubscribeResp userSubscribeResp = sysLoginService.getUserSub(item.getUserId());
            userSubscribeResps.add(userSubscribeResp);
        }
        return Result.success(userSubscribeResps);
    }

    //查询某个用户粉丝的列表对象
    @GetMapping("/getUserFans")
    public Result<List<UserSubscribeResp>> userFansResult (@RequestParam("userId") Long userId){
        List<FansTable> fansTables = service.userFansQuery(userId);
        if (CollectionUtil.isEmpty(fansTables)){
            return Result.success(null);
        }
        List<UserSubscribeResp> userSubscribeResps = new ArrayList<>();
        for (FansTable item : fansTables){
            UserSubscribeResp userSubscribeResp = sysLoginService.getUserSub(item.getFansId());
            userSubscribeResps.add(userSubscribeResp);
        }
        return Result.success(userSubscribeResps);
    }

    //新建关注
    @PostMapping("/insertSubscribe")
    public Result<Boolean> userSubscribeInsert (@RequestBody UserSubscribeInsertReq req){
        boolean result = service.userSubscribeInsert(req);
        return Result.success(result);
    }

    //取消关注
    @PostMapping("/deleteSubscribe")
    public Result<Boolean> userSubscribeDelete (@RequestBody UserSubscribeInsertReq req){
        boolean result = service.userSubscribeDelete(req);
        return Result.success(result);
    }

    //查看当前用户对搜索用户的关注状态
    @PostMapping("/checkUserSubStatus")
    public Result<Boolean> checkUserSubStatus(@RequestBody UserSubscribeInsertReq req){
        boolean result = service.getUserStatus(req);
        return Result.success(result);
    }

}
