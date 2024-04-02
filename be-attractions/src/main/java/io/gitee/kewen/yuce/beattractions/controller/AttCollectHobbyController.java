package io.gitee.kewen.yuce.beattractions.controller;

import io.gitee.kewen.yuce.beattractions.dto.resp.AttHomePageQueryResp;
import io.gitee.kewen.yuce.beattractions.service.AttTableSingleService;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import io.gitee.kewen.yuce.beattractions.service.CollectTableService;
import io.gitee.kewen.yuce.beattractions.service.HobbyTableService;
import io.gitee.kewen.yuce.common.service.AttPictureService;
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
 * @data 2024/4/2 10:18
 */
@RestController
@RequestMapping("/attCollect")
public class AttCollectHobbyController {
    @Resource
    private CollectTableService collectTableService;

    @Resource
    private HobbyTableService hobbyTableService;

    @Resource
    private AttTableSingleService attTableSingleService;

    @Resource
    private AttPictureService attPictureService;

    @GetMapping("/collectQuery")
     public Result<List<AttHomePageQueryResp>> attCollectList (@RequestParam("userId") Long userId){
        List<AttTableSingle> attTableSingles = collectTableService.selectByUserId(userId);
        List<AttHomePageQueryResp> attHomePageQueryResps = new ArrayList<>();
        for (AttTableSingle item : attTableSingles){
            String picture = attPictureService.getByAttId(item.getId());
            AttHomePageQueryResp attHomePageQueryResp = new AttHomePageQueryResp(picture,item.getId(),item.getAttName(),item.getAttAddress(),item.getScore());
            attHomePageQueryResps.add(attHomePageQueryResp);
        }
        return Result.success(attHomePageQueryResps);
     }

    @GetMapping("/hobbyQuery")
    public Result<List<AttHomePageQueryResp>> hobbyCollectList (@RequestParam("userId") Long userId){
        List<AttTableSingle> attTableSingles = hobbyTableService.selectByUserId(userId);
        List<AttHomePageQueryResp> attHomePageQueryResps = new ArrayList<>();
        for (AttTableSingle item : attTableSingles){
            String picture = attPictureService.getByAttId(item.getId());
            AttHomePageQueryResp attHomePageQueryResp = new AttHomePageQueryResp(picture,item.getId(),item.getAttName(),item.getAttAddress(),item.getScore());
            attHomePageQueryResps.add(attHomePageQueryResp);
        }
        return Result.success(attHomePageQueryResps);
    }
}