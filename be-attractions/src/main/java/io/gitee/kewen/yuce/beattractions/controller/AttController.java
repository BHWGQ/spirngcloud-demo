package io.gitee.kewen.yuce.beattractions.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import io.gitee.kewen.yuce.beattractions.dto.resp.AttHomePageQueryResp;
import io.gitee.kewen.yuce.beattractions.dto.resp.AttHomePageQueryTen;
import io.gitee.kewen.yuce.beattractions.exception.QueryException;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.feign.CommentFeignClient;
import io.gitee.kewen.yuce.common.feign.HotelFeignClient;
import io.gitee.kewen.yuce.common.feign.RestFoodFeignClient;
import io.gitee.kewen.yuce.common.feign.WeatherFeignClient;
import io.gitee.kewen.yuce.common.model.dto.resp.*;
import io.gitee.kewen.yuce.common.model.entity.AttPicture;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import io.gitee.kewen.yuce.common.model.entity.CommentTable;
import io.gitee.kewen.yuce.common.service.AttPictureService;
import io.gitee.kewen.yuce.beattractions.service.AttTableSingleService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/att")
@Validated
public class AttController {
    @Resource
    private AttPictureService attPictureService;

    @Resource
    private AttTableSingleService attTableSingleService;

    @Resource
    private CommentFeignClient commentFeignClient;

    @Resource
    private HotelFeignClient hotelFeignClient;

    @Resource
    private RestFoodFeignClient restFoodFeignClient;

    @Resource
    private WeatherFeignClient weatherFeignClient;

    @Resource
    private RedisTemplate<String,List<AttHomePageQueryTen>> redisTemplate;

    @Resource
    private RedissonClient redissonClient;

    @GetMapping("/homePageQuery")
    public Result<List<AttHomePageQueryResp>> queryRespResult (){
        List<AttHomePageQueryTen> homePageQueryTens =  redisTemplate.opsForValue().get("homePageQuery");
        if (CollectionUtil.isEmpty(homePageQueryTens)){
            RLock lock = redissonClient.getLock("homePageQuery");
            lock.lock();
            try {
                homePageQueryTens = redisTemplate.opsForValue().get("homePageQuery");
                if (CollectionUtil.isEmpty(homePageQueryTens)) {
                    homePageQueryTens = attTableSingleService.queryTenInfo();
                }
            } finally {
                lock.unlock();
            }
        }
//        List<AttHomePageQueryTen> attHomePageQueryTens = attTableSingleService.queryTenInfo();
        List<AttHomePageQueryResp> attHomePageQueryResps = new ArrayList<>();
        for (AttHomePageQueryTen item: homePageQueryTens){
            String byId = attPictureService.getByAttId(item.getId());
            AttHomePageQueryResp attHomePageQueryResp = new AttHomePageQueryResp(byId,item.getId(),item.getAttName(),item.getAttAddress(),item.getScore());
            attHomePageQueryResps.add(attHomePageQueryResp);
        }
        return Result.success(attHomePageQueryResps);
    }

    @GetMapping("/{id}")
    public Result<ClickPageResp> attTableSingleResult (@PathVariable Integer id){
        AttTableSingle attTableSingle = attTableSingleService.getById(id);
        if (ObjectUtil.isEmpty(attTableSingle)){
            throw QueryException.Data_Not_Exist;
        }
        String picture = attPictureService.getByAttId(attTableSingle.getId());
        Result<List<CommentTable>> listResult = commentFeignClient.commentTableResult(attTableSingle.getId());
        Result<List<HotelRecommendThreeResp>> listResult1 = hotelFeignClient.threeRespResult(attTableSingle.getAddressId());
        Result<List<FoodRecommendThreeResp>> listResult2 = restFoodFeignClient.foodRecommendThreeRespResult(attTableSingle.getAddressId());
        Result<WeatherResponse> listResult3 = weatherFeignClient.weatherResponseResult(attTableSingle.getAttAddress());
        ClickPageResp clickPageResp = new ClickPageResp(attTableSingle,listResult.getData(),listResult1.getData(),listResult2.getData(),picture,listResult3.getData());
        return Result.success(clickPageResp);
    }

    @GetMapping("/attQueryByAttName")
    public Result<List<AttQueryByAttNameResp>> result ( @RequestParam("attName") @NotBlank(message = "搜索内容不能为空") String attName){
        List<AttTableSingle> attTableSingle = attTableSingleService.getByAttName(attName);
        List<AttQueryByAttNameResp> attQueryByAttNameResps = new ArrayList<>();
        for (AttTableSingle item : attTableSingle){
            String pictureUrl = attPictureService.getByAttId(item.getId());
            AttQueryByAttNameResp attQueryByAttNameResp = new AttQueryByAttNameResp(pictureUrl, item.getId(),item.getAttName(),item.getAttAddress(),item.getScore());
            attQueryByAttNameResps.add(attQueryByAttNameResp);
        }
        return Result.success(attQueryByAttNameResps);
    }
}
