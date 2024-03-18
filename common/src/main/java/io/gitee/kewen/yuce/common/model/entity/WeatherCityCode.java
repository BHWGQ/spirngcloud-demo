package io.gitee.kewen.yuce.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * (WeatherCityCode)表实体类
 *
 * @author makejava
 * @since 2024-03-15 19:51:48
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("weather_city_code")
public class WeatherCityCode implements Serializable {

    private static final long serialVersionUID = -28218995171091886L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("city_name")
    private String cityName;

    @TableField("ad_code")
    private String adCode;


}

