package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.RecommendFoodCommentMapper;
import io.gitee.kewen.yuce.common.mapper.RecommendFoodTableMapper;
import io.gitee.kewen.yuce.common.mapper.SysLoginMapper;
import io.gitee.kewen.yuce.common.model.dto.req.RestCommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.model.dto.resp.FoodRecommendThreeResp;
import io.gitee.kewen.yuce.common.model.dto.resp.HotelRecommendThreeResp;
import io.gitee.kewen.yuce.common.model.entity.*;
import io.gitee.kewen.yuce.common.service.RecommendFoodCommentService;
import io.gitee.kewen.yuce.common.service.RecommendFoodPictureService;
import io.gitee.kewen.yuce.common.service.RecommendFoodTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * (RecommendFoodComment)表服务实现类
 *
 * @author makejava
 * @since 2024-03-30 19:23:36
 */
@Service("recommendFoodCommentService")
public class RecommendFoodCommentServiceImpl extends ServiceImpl<RecommendFoodCommentMapper, RecommendFoodComment> implements RecommendFoodCommentService {
    @Resource
    private SysLoginMapper loginMapper;

    @Resource
    private RecommendFoodCommentMapper recommendFoodCommentMapper;

    @Resource
    private RecommendFoodPictureService recommendFoodPictureService;

    @Resource
    private RecommendFoodTableMapper recommendFoodTableMapper;

    @Override
    public CommentInsertResp insert(RestCommentInsertReq req) {
        LambdaQueryWrapper<LoginTable> lambdaQueryWrapper = new QueryWrapper<LoginTable>().lambda()
                .eq(LoginTable::getUserId,req.getUserId())
                .eq(LoginTable::getStatus,0);
        LoginTable loginTable = loginMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtil.isNull(loginTable)){
            throw new RuntimeException("您已被暂时封禁，无法发言");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        RecommendFoodComment recommendFoodComment = new RecommendFoodComment();
        recommendFoodComment.setRestId(req.getRestId());
        recommendFoodComment.setUserName(req.getUserName());
        recommendFoodComment.setUserId(req.getUserId());
        recommendFoodComment.setContent(req.getContent());
        recommendFoodComment.setReply(req.getReply());
        recommendFoodComment.setCreateTime(currentTime);
        int affectRows = recommendFoodCommentMapper.insert(recommendFoodComment);
        if (affectRows == 0){
            throw new RuntimeException("评论失败，正在抢修!");
        }
        return new CommentInsertResp(req.getUserName());
    }

    @Override
    public List<FoodRecommendThreeResp>  queryByUserId(Long userId) {
        List<RecommendFoodComment> recommendFoodComments = recommendFoodCommentMapper.selectByUserId(userId);
        if (CollectionUtil.isEmpty(recommendFoodComments)){
            return null;
        }
        List<FoodRecommendThreeResp> foodRecommendThreeResps = new ArrayList<>();
        for (RecommendFoodComment item : recommendFoodComments){
            int  restId = item.getRestId();
            String picture = recommendFoodPictureService.getPicture(restId);
            LambdaQueryWrapper<RecommendFoodTable> wrapper = new QueryWrapper<RecommendFoodTable>().lambda()
                    .eq(RecommendFoodTable::getRestId,restId);
            RecommendFoodTable recommendFoodTable = recommendFoodTableMapper.selectOne(wrapper);
            FoodRecommendThreeResp foodRecommendThreeResp = new FoodRecommendThreeResp(picture,recommendFoodTable.getAddressId(),recommendFoodTable.getRestName(),recommendFoodTable.getRecommendFood(),restId);
            foodRecommendThreeResps.add(foodRecommendThreeResp);
        }
        return foodRecommendThreeResps;
    }
}

