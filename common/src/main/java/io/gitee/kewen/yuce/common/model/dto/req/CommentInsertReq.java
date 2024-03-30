package io.gitee.kewen.yuce.common.model.dto.req;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentInsertReq {
    private Integer attId;

    private String userName;

    private Long userId;

    private String content;

    private Integer reply;
}
