package io.gitee.kewen.yuce.common.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelCommentInsertReq {
    private Integer travelId;

    private String userName;

    private Long userId;

    private String content;

    private Integer reply;
}
