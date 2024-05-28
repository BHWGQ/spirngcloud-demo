package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.*;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/5/28 9:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserSubscribeResp {
    private String picture;

    private Long userId;

    private String userName;

    private String signature;
}
