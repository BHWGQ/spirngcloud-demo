package io.gitee.kewen.yuce.becomment.controller;

import io.gitee.kewen.yuce.common.model.dto.req.HotelCommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.req.RestCommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.req.TravelCommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.req.CommentInsertReq;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodComment;
import io.gitee.kewen.yuce.common.service.CommentTableService;
import io.gitee.kewen.yuce.common.service.HotelCommentService;
import io.gitee.kewen.yuce.common.service.RecommendFoodCommentService;
import io.gitee.kewen.yuce.common.service.TravelCommentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentInsertController {
    @Resource
    private CommentTableService commentTableService;

    @Resource
    private RecommendFoodCommentService recommendFoodCommentService;

    @Resource
    private HotelCommentService hotelCommentService;

    @Resource
    private TravelCommentService travelCommentService;

    @PostMapping("/insert")
    public Result<CommentInsertResp> commentInsertRespResult (@RequestBody CommentInsertReq req){
        CommentInsertResp commentInsertResp = commentTableService.insert(req);
        return Result.success(commentInsertResp);
    }

    @PostMapping("/restCommentInsert")
    public Result<CommentInsertResp> commentInsertResp (@RequestBody RestCommentInsertReq req){
        CommentInsertResp commentInsertResp = recommendFoodCommentService.insert(req);
        return Result.success(commentInsertResp);
    }

    @PostMapping("/hotelCommentInsert")
    public Result<CommentInsertResp> hotelCommentInsert(@RequestBody HotelCommentInsertReq req){
        CommentInsertResp commentInsertResp = hotelCommentService.insert(req);
        return Result.success(commentInsertResp);
    }

    @PostMapping("/travelCommentInsert")
    public Result<CommentInsertResp> travelResult(@RequestBody TravelCommentInsertReq req){
        CommentInsertResp commentInsertResp = travelCommentService.insert(req);
        return Result.success(commentInsertResp);
    }
}
