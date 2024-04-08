package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/8 16:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelOneResp {
    private Integer id;

    private LocalDateTime createTime;

    private String attName;

    private String attPicture;

    private String address;

    private String introduce;
}
