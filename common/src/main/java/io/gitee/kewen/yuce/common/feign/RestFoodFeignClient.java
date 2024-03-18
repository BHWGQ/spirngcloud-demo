package io.gitee.kewen.yuce.common.feign;

import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.resp.FoodRecommendThreeResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "food-service",path = "/food/foodQuery")
public interface RestFoodFeignClient {

    @GetMapping("/recRest")
    Result<List<FoodRecommendThreeResp>> foodRecommendThreeRespResult (@RequestParam("addressId") Integer addressId);
}
