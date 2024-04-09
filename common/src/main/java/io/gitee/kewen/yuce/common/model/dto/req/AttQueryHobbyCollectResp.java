package io.gitee.kewen.yuce.common.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/9 18:41
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttQueryHobbyCollectResp {
    private Boolean hobby;

    private Boolean collect;
}
