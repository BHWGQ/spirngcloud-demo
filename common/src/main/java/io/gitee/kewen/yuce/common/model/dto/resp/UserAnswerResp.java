package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/8 13:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAnswerResp {
    List<Att_user> att_users;

    List<HotelRecommendThreeResp> hotelRecommendThreeResps;

    List<FoodRecommendThreeResp> foodRecommendThreeResps;

    List<TravelQueryResp> travelQueryResps;
}
