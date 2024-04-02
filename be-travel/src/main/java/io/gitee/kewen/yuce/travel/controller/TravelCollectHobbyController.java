package io.gitee.kewen.yuce.travel.controller;

import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.feign.PortalClient;
import io.gitee.kewen.yuce.common.mapper.TravelTableMapper;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelQueryResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelTenInfoResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelUserInfoResp;
import io.gitee.kewen.yuce.common.model.entity.CollectTravelTable;
import io.gitee.kewen.yuce.common.model.entity.HobbyTravelTable;
import io.gitee.kewen.yuce.common.service.CollectTravelTableService;
import io.gitee.kewen.yuce.common.service.HobbyTravelTableService;
import io.gitee.kewen.yuce.common.service.TravelTableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/2 12:27
 */
@RestController
@RequestMapping("/travelCollect")
public class TravelCollectHobbyController {

    @Resource
    private CollectTravelTableService collectTravelTableService;

    @Resource
    private HobbyTravelTableService hobbyTravelTableService;

    @Resource
    private TravelTableService travelTableService;

    @Resource
    private TravelTableMapper travelTableMapper;

    @Resource
    private PortalClient portalClient;

    @GetMapping("/collectQuery")
    public Result<List<TravelQueryResp>> travelCollectQuery(@RequestParam("userId") Long userId){
        List<CollectTravelTable> collectTravelTables = collectTravelTableService.getByUserId(userId);
        List<TravelTenInfoResp> travelTenInfoResps = new ArrayList<>();
        for (CollectTravelTable item : collectTravelTables){
            TravelTenInfoResp travelTenInfoResp = travelTableMapper.selectByTravelId(item.getTravelId());
            travelTenInfoResps.add(travelTenInfoResp);
        }
        List<TravelQueryResp> travelQueryResps = new ArrayList<>();
        for (TravelTenInfoResp item : travelTenInfoResps){
            Result<TravelUserInfoResp> travelUserInfoResp = portalClient.result(item.getUserId());
            TravelQueryResp travelQueryResp = new TravelQueryResp(item.getId(),travelUserInfoResp.getData().getUserName(),item.getCreateTime(),item.getAttName(),item.getAttPicture(),travelUserInfoResp.getData().getUserPicture(),item.getAddress(),item.getIntroduce());
            travelQueryResps.add(travelQueryResp);
        }
        return Result.success(travelQueryResps);
    }

    @GetMapping("/hobbyQuery")
    public Result<List<TravelQueryResp>> travelHobbyQuery(@RequestParam("userId") Long userId){
        List<HobbyTravelTable> hobbyTravelTables = hobbyTravelTableService.getByUserId(userId);
        List<TravelTenInfoResp> travelTenInfoResps = new ArrayList<>();
        for (HobbyTravelTable item : hobbyTravelTables){
            TravelTenInfoResp travelTenInfoResp = travelTableMapper.selectByTravelId(item.getTravelId());
            travelTenInfoResps.add(travelTenInfoResp);
        }
        List<TravelQueryResp> travelQueryResps = new ArrayList<>();
        for (TravelTenInfoResp item : travelTenInfoResps){
            Result<TravelUserInfoResp> travelUserInfoResp = portalClient.result(item.getUserId());
            TravelQueryResp travelQueryResp = new TravelQueryResp(item.getId(),travelUserInfoResp.getData().getUserName(),item.getCreateTime(),item.getAttName(),item.getAttPicture(),travelUserInfoResp.getData().getUserPicture(),item.getAddress(),item.getIntroduce());
            travelQueryResps.add(travelQueryResp);
        }
        return Result.success(travelQueryResps);
    }
}
