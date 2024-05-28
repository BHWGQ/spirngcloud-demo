package io.gitee.kewen.yuce.common.model.dto.req;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/5/28 14:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserSubscribeInsertReq {
    @NotNull(message = "缺少被关注用户")
    private Long userId;

    @NotNull(message = "缺少关注主体")
    private Long fansId;
}
