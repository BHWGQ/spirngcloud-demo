package io.gitee.kewen.yuce.travel.controller;

import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.feign.PortalClient;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelQueryResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelTenInfoResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelUserInfoResp;
import io.gitee.kewen.yuce.common.model.entity.TravelTable;
import io.gitee.kewen.yuce.common.service.TravelPictureTableService;
import io.gitee.kewen.yuce.common.service.TravelTableService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/travel")
public class TravelQueryController {

    @Resource
    private TravelTableService travelTableService;

    @Resource
    private TravelPictureTableService travelPictureTableService;

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

}
