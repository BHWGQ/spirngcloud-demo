package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRecommendResp {
    private Integer addressId;

    private String hotelName;

    private String hotelAddress;

    private Integer hotelId;
}
