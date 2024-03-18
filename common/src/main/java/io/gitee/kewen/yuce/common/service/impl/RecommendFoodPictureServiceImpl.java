package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.exception.RestException.RestException;
import io.gitee.kewen.yuce.common.mapper.RecommendFoodPictureMapper;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodPicture;
import io.gitee.kewen.yuce.common.service.RecommendFoodPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (RecommendFoodPicture)表服务实现类
 *
 * @author makejava
 * @since 2024-03-10 12:20:46
 */
@Service("recommendFoodPictureService")
public class RecommendFoodPictureServiceImpl extends ServiceImpl<RecommendFoodPictureMapper, RecommendFoodPicture> implements RecommendFoodPictureService {

    @Resource
    private RecommendFoodPictureMapper recommendFoodPictureMapper;

    @Override
    public String getPicture(Integer restId) {
        LambdaQueryWrapper<RecommendFoodPicture> foodPictureLambdaQueryWrapper = new QueryWrapper<RecommendFoodPicture>().lambda()
                .eq(RecommendFoodPicture::getRestId,restId);
        RecommendFoodPicture recommendFoodPicture = recommendFoodPictureMapper.selectOne(foodPictureLambdaQueryWrapper);
        if (ObjectUtil.isNull(recommendFoodPicture)){
            return "没有该餐厅的图片哦";
        }
        return recommendFoodPicture.getFoodPicture();
    }
}

