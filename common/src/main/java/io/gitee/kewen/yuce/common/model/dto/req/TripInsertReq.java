package io.gitee.kewen.yuce.common.model.dto.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime leaveTime;

    private String originAdd;

    private String desAdd;
}
