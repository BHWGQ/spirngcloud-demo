package io.gitee.kewen.yuce.common.model.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import io.gitee.kewen.yuce.common.model.entity.CommentTable;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/27 13:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CommentTableQueryResp {
    private CommentTable commentTable;

    private String picture;
}
