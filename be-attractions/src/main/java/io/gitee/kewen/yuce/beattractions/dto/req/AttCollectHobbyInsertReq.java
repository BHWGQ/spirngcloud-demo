package io.gitee.kewen.yuce.beattractions.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/2 14:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttCollectHobbyInsertReq {
    private Long userId;

    private Integer attId;
}
