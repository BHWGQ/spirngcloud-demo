package io.gitee.kewen.yuce.common.model.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodRecommendThreeResp {
    private String foodPicture;

    private Integer addressId;

    private String restName;

    private String recommendFood;

    private Integer restId;
}
