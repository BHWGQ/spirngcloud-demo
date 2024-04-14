package io.gitee.kewen.yuce.common.model.dto.req;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/10 19:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripInsertReq implements Serializable {
    private Long userId;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime leaveTime;

    private String originAdd;

    private String desAdd;
}
