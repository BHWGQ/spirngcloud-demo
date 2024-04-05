package io.gitee.kewen.yuce.beattractions.config;

import io.gitee.kewen.yuce.common.feign.CommentFeignClient;
import io.gitee.kewen.yuce.common.feign.HotelFeignClient;
import io.gitee.kewen.yuce.common.feign.RestFoodFeignClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

//@Component
public class FeignClientInitializer {

    @Resource
    private CommentFeignClient commentFeignClient;

    @Resource
    private HotelFeignClient hotelFeignClient;

    @Resource
    private RestFoodFeignClient restFoodFeignClient;

    @PostConstruct
    public void init(){
        commentFeignClient.commentTableResult(1);
        hotelFeignClient.threeRespResult(1);
        restFoodFeignClient.foodRecommendThreeRespResult(1);
    }

}
