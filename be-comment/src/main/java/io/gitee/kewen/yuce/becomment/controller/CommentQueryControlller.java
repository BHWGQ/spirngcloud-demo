package io.gitee.kewen.yuce.becomment.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.exception.CommentException.CommentException;
import io.gitee.kewen.yuce.common.feign.PortalClient;
import io.gitee.kewen.yuce.common.model.dto.resp.*;
import io.gitee.kewen.yuce.common.model.entity.CommentTable;
import io.gitee.kewen.yuce.common.model.entity.HotelComment;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodComment;
import io.gitee.kewen.yuce.common.model.entity.TravelComment;
import io.gitee.kewen.yuce.common.service.CommentTableService;
import io.gitee.kewen.yuce.common.service.HotelCommentService;
import io.gitee.kewen.yuce.common.service.RecommendFoodCommentService;
import io.gitee.kewen.yuce.common.service.TravelCommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/com")
public class CommentQueryControlller {
    @Resource
    private CommentTableService commentTableService;

    @Resource
    private RecommendFoodCommentService recommendFoodCommentService;

    @Resource
    private HotelCommentService hotelCommentService;

    @Resource
    private TravelCommentService travelCommentService;

    @Resource
    private PortalClient portalClient;


    @GetMapping("/query")
    public Result<List<CommentTable>> commentTableResult (@RequestParam("Id") Integer Id){
        List<CommentTable> commentTable = commentTableService.list(new LambdaQueryWrapper<CommentTable>().eq(CommentTable::getAttId,Id));
        if (CollectionUtil.isEmpty(commentTable)){
            return Result.success(null);
        }
//        List<CommentTableQueryResp> commentTableQueryResps = new ArrayList<>();
//        for (CommentTable item : commentTable){
//            Result<String> picture = portalClient.getUserPicture(item.getUserId());
//            CommentTableQueryResp commentTableQueryResp = new CommentTableQueryResp(item,picture.getData());
//            commentTableQueryResps.add(commentTableQueryResp);
//        }
        return Result.success(commentTable);
    }

    @GetMapping("/queryRest")
    public Result<List<RecommendFoodComment>> listResult (@RequestParam("id") Integer id){
        List<RecommendFoodComment> recommendFoodComments = recommendFoodCommentService.list(new LambdaQueryWrapper<RecommendFoodComment>().eq(RecommendFoodComment::getRestId,id));
        if (CollectionUtil.isEmpty(recommendFoodComments)){
            return Result.success(null);
        }
        return Result.success(recommendFoodComments);
    }

    @GetMapping("/queryHotel")
    public Result<List<HotelComment>> hotelListResult(@RequestParam("id") Integer id){
        List<HotelComment> hotelComments = hotelCommentService.list(new LambdaQueryWrapper<HotelComment>().eq(HotelComment::getHotelId,id));
        if (CollectionUtil.isEmpty(hotelComments)){
            return Result.success(null);
        }
        return Result.success(hotelComments);
    }

    @GetMapping("/queryTravel")
    public Result<List<TravelComment>> travelListResult(@RequestParam("id") Integer id){
        List<TravelComment> travelComments = travelCommentService.list(new LambdaQueryWrapper<TravelComment>().eq(TravelComment::getTravelId,id));
        if (CollectionUtil.isEmpty(travelComments)){
            return Result.success(null);
        }
        return Result.success(travelComments);
    }

    @GetMapping("/userAnswer")
    public Result<UserAnswerResp> userAnswer(@RequestParam("userId") Long userId){
        List<Att_user> att_users= commentTableService.queryByUserId(userId);
        List<HotelRecommendThreeResp> hotelRecommendThreeResps = hotelCommentService.queryByUserId(userId);
        List<FoodRecommendThreeResp> foodRecommendThreeResps = recommendFoodCommentService.queryByUserId(userId);
        List<TravelOneResp> resps = travelCommentService.queryByUserId(userId);
        Result<TravelUserInfoResp> travelQueryResps = portalClient.result(userId);
        List<TravelQueryResp> resps1 = new ArrayList<>();
        for (TravelOneResp item : resps){
            TravelQueryResp resp = new TravelQueryResp(item.getId(),travelQueryResps.getData().getUserName(),item.getCreateTime(),item.getAttName(),item.getAttPicture(),travelQueryResps.getData().getUserPicture(),item.getAddress(),item.getIntroduce());
            resps1.add(resp);
        }
        return Result.success(new UserAnswerResp(att_users,hotelRecommendThreeResps,foodRecommendThreeResps,resps1));
    }
}
