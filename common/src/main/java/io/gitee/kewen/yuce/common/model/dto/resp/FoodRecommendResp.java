package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodRecommendResp {
    private Integer addressId;

    private String restName;

    private String recommendFood;

    private Integer restId;
}
