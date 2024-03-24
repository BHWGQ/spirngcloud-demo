package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelTenInfoResp {
    private Integer id;
    private LocalDateTime createTime;

    private Long userId;
    private String attPicture;
    private String attName;
}
