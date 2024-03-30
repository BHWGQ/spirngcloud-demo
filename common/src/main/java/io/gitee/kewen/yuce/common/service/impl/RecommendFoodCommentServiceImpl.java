package io.gitee.kewen.yuce.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.RecommendFoodCommentMapper;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodComment;
import io.gitee.kewen.yuce.common.service.RecommendFoodCommentService;
import org.springframework.stereotype.Service;

/**
 * (RecommendFoodComment)表服务实现类
 *
 * @author makejava
 * @since 2024-03-30 19:23:36
 */
@Service("recommendFoodCommentService")
public class RecommendFoodCommentServiceImpl extends ServiceImpl<RecommendFoodCommentMapper, RecommendFoodComment> implements RecommendFoodCommentService {

}

