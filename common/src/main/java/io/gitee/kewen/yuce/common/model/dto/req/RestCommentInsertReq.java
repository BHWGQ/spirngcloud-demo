package io.gitee.kewen.yuce.common.model.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestCommentInsertReq {
    private Integer restId;

    private String userName;

    private Long userId;

    private String content;

    private Integer reply;
}
