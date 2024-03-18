package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.consts.WeatherConsts;
import io.gitee.kewen.yuce.common.mapper.WeatherCityCodeMapper;
import io.gitee.kewen.yuce.common.model.entity.WeatherCityCode;
import io.gitee.kewen.yuce.common.service.WeatherCityCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (WeatherCityCode)表服务实现类
 *
 * @author makejava
 * @since 2024-03-15 19:51:49
 */
@Service("weatherCityCodeService")
public class WeatherCityCodeServiceImpl extends ServiceImpl<WeatherCityCodeMapper, WeatherCityCode> implements WeatherCityCodeService {

    @Resource
    private WeatherCityCodeMapper weatherCityCodeMapper;

    @Override
    public String getWeatherUrl(String cityName) {
        LambdaQueryWrapper<WeatherCityCode> weatherCityCodeLambdaQueryWrapper = new QueryWrapper<WeatherCityCode>().lambda()
                .eq(WeatherCityCode::getCityName,cityName);
        WeatherCityCode weatherCityCode = weatherCityCodeMapper.selectOne(weatherCityCodeLambdaQueryWrapper);
        if (ObjectUtil.isNull(weatherCityCode)){
            throw new RuntimeException("未查询到该地信息，请确保您输入的城市名称正确");
        }
        String adCode = weatherCityCode.getAdCode();
        return WeatherConsts.URL + adCode + WeatherConsts.k + WeatherConsts.KEY + WeatherConsts.ex;
    }
}

