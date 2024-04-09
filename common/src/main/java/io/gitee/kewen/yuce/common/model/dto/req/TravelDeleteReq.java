package io.gitee.kewen.yuce.common.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/8 19:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelDeleteReq {
    private Long userId;
    private Integer id;
}
