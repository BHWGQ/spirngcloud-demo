package io.gitee.kewen.yuce.beweather.controller;

import com.google.gson.Gson;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.resp.WeatherResponse;
import io.gitee.kewen.yuce.common.service.WeatherCityCodeService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/weather")
public class WeatherQueryController {
    @Resource
    private WeatherCityCodeService weatherCityCodeService;

    private final Gson gson = new Gson();

    @GetMapping("/Query")
    public Result<WeatherResponse> weatherResponseResult (@RequestParam("cityName") String cityName) throws IOException {
        String url = weatherCityCodeService.getWeatherUrl(cityName);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)){
            String jsonResponse = EntityUtils.toString(response.getEntity());
            return Result.success(gson.fromJson(jsonResponse,WeatherResponse.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
