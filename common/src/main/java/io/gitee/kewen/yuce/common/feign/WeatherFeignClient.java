package io.gitee.kewen.yuce.common.feign;

import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.resp.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@FeignClient(value = "weather-service",path = "/weather/weather")
public interface WeatherFeignClient {
    @GetMapping("/Query")
    Result<WeatherResponse> weatherResponseResult (@RequestParam("cityName") String cityName);
}
