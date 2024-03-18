package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Forecast {
    private String city;
    private String adcode;
    private String province;
    private String reporttime;
    private List<Weather> casts;
}
