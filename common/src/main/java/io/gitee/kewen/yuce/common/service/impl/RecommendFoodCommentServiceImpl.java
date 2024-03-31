package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.RecommendFoodCommentMapper;
import io.gitee.kewen.yuce.common.mapper.SysLoginMapper;
import io.gitee.kewen.yuce.common.model.dto.req.RestCommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodComment;
import io.gitee.kewen.yuce.common.service.RecommendFoodCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

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
}

