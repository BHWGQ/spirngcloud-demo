package io.gitee.kewen.yuce.common.model.dto.resp;

import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import io.gitee.kewen.yuce.common.model.entity.CommentTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClickPageResp {
    private AttTableSingle attTableSingle;

    private List<CommentTable> commentTable;

    private List<HotelRecommendThreeResp> hotelRecommendThreeResp;

    private List<FoodRecommendThreeResp> foodRecommendThreeResp;
}
