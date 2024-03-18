package io.gitee.kewen.yuce.common.feign;

import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.resp.HotelRecommendThreeResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "hotel-service",path = "/hotel/hotelQuery")
public interface HotelFeignClient {

    @GetMapping("/recHotel")
    Result<List<HotelRecommendThreeResp>> threeRespResult (@RequestParam("addressId") Integer addressId);
}
