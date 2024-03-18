package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.entity.WeatherCityCode;

/**
 * (WeatherCityCode)表服务接口
 *
 * @author makejava
 * @since 2024-03-15 19:51:49
 */
public interface WeatherCityCodeService extends IService<WeatherCityCode> {

    String getWeatherUrl(String cityName);
}

