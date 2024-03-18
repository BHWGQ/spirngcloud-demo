package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WeatherResponse {
    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<Forecast> forecasts;
}

