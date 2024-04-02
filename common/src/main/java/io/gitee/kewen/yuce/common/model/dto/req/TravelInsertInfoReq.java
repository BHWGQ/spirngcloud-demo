package io.gitee.kewen.yuce.common.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelInsertInfoReq {
    private Long userId;

    private Integer travelId;
}
