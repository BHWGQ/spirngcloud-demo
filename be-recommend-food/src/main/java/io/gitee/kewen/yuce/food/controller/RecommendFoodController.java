package io.gitee.kewen.yuce.food.controller;

import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.resp.FoodRecommendResp;
import io.gitee.kewen.yuce.common.model.dto.resp.FoodRecommendThreeResp;
import io.gitee.kewen.yuce.common.model.dto.resp.RestIntriduceResp;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodPicture;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodTable;
import io.gitee.kewen.yuce.common.service.RecommendFoodPictureService;
import io.gitee.kewen.yuce.common.service.RecommendFoodTableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/foodQuery")
public class RecommendFoodController {
    @Resource
    private RecommendFoodTableService recommendFoodTableService;

    @Resource
    private RecommendFoodPictureService recommendFoodPictureService;

    @GetMapping("/recRest")
    public Result<List<FoodRecommendThreeResp>> foodRecommendThreeRespResult (@RequestParam("addressId") Integer addressId){
        List<FoodRecommendResp> foodRecommendResp = recommendFoodTableService.getThree(addressId);
        List<FoodRecommendThreeResp> food = new ArrayList<>();
        for (FoodRecommendResp item : foodRecommendResp){
            String pictureUrl = recommendFoodPictureService.getPicture(item.getRestId());
            FoodRecommendThreeResp foodRecommendThreeResp = new FoodRecommendThreeResp(pictureUrl,item.getAddressId(),item.getRestName(),item.getRecommendFood(),item.getRestId());
            food.add(foodRecommendThreeResp);
        }
        return Result.success(food);
    }

    @GetMapping("/rec")
    public Result<List<FoodRecommendThreeResp>> food (){
        List<FoodRecommendResp> foodRecommendResp = recommendFoodTableService.getThreeresp();
        List<FoodRecommendThreeResp> food = new ArrayList<>();
        for (FoodRecommendResp item : foodRecommendResp){
            String pictureUrl = recommendFoodPictureService.getPicture(item.getRestId());
            FoodRecommendThreeResp foodRecommendThreeResp = new FoodRecommendThreeResp(pictureUrl,item.getAddressId(),item.getRestName(),item.getRecommendFood(),item.getRestId());
            food.add(foodRecommendThreeResp);
        }
        return Result.success(food);
    }

    @GetMapping("/Query")
    public Result<RestIntriduceResp> restIntriduceRespResult (@RequestParam("restId") Integer restId){
        RecommendFoodTable recommendFoodTable = recommendFoodTableService.getByRestId(restId);
        String pictureUrl = recommendFoodPictureService.getPicture(recommendFoodTable.getRestId());
        RestIntriduceResp resp = new RestIntriduceResp(pictureUrl,recommendFoodTable.getRestName(),recommendFoodTable.getSpecificAddress(),recommendFoodTable.getIntroduce(),recommendFoodTable.getRecommendFood(),recommendFoodTable.getPerPrice(),recommendFoodTable.getPhoneNumber(),recommendFoodTable.getRestId());
        return Result.success(resp);
    }
    
}
