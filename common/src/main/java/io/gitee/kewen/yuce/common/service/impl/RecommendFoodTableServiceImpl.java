package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.exception.RestException.RestException;
import io.gitee.kewen.yuce.common.mapper.RecommendFoodTableMapper;
import io.gitee.kewen.yuce.common.model.dto.resp.FoodRecommendResp;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodTable;
import io.gitee.kewen.yuce.common.service.RecommendFoodTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (RecommendFoodTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("recommendFoodTableService")
public class RecommendFoodTableServiceImpl extends ServiceImpl<RecommendFoodTableMapper, RecommendFoodTable> implements RecommendFoodTableService {

    @Resource
    private RecommendFoodTableMapper recommendFoodTableMapper;

    @Override
    public List<FoodRecommendResp> getThree(Integer addressId) {
        List<RecommendFoodTable> foodRecommendResps = recommendFoodTableMapper.selectRandomRest(addressId);
        if (ObjectUtil.isEmpty(foodRecommendResps)){
            throw RestException.Add_No_Rest;
        }
        List<FoodRecommendResp> foodRecommendResps1 = new ArrayList<>();
        for (RecommendFoodTable item : foodRecommendResps){
            FoodRecommendResp foodRecommendResp = new FoodRecommendResp(item.getAddressId(),item.getRestName(),item.getRecommendFood(),item.getRestId());
            foodRecommendResps1.add(foodRecommendResp);
        }
        return foodRecommendResps1;
    }

    @Override
    public List<FoodRecommendResp> getThreeresp() {
        List<RecommendFoodTable> foodRecommendResps = recommendFoodTableMapper.selectRandom();
        if (ObjectUtil.isEmpty(foodRecommendResps)){
            throw RestException.Query_No_Rest;
        }
        List<FoodRecommendResp> foodRecommendResps1 = new ArrayList<>();
        for (RecommendFoodTable item : foodRecommendResps){
            FoodRecommendResp foodRecommendResp = new FoodRecommendResp(item.getAddressId(),item.getRestName(),item.getRecommendFood(),item.getRestId());
            foodRecommendResps1.add(foodRecommendResp);
        }
        return foodRecommendResps1;
    }

    @Override
    public RecommendFoodTable getByRestId(Integer restId) {
        LambdaQueryWrapper<RecommendFoodTable> recommendFoodTableLambdaQueryWrapper = new QueryWrapper<RecommendFoodTable>().lambda()
                .eq(RecommendFoodTable::getRestId,restId);
        RecommendFoodTable recommendFoodTable = recommendFoodTableMapper.selectOne(recommendFoodTableLambdaQueryWrapper);
        if (ObjectUtil.isNull(recommendFoodTable)){
            throw RestException.Query_Error;
        }
        return recommendFoodTable;
    }
}

