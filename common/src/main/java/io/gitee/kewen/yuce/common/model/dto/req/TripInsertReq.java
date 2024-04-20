package io.gitee.kewen.yuce.common.model.dto.req;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "必须有用户账号")
    private Long userId;

    @NotBlank(message = "必须要填写内容")
    private String content;

    @NotNull(message = "没有填写提醒时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @NotNull(message = "没有填写离开时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime leaveTime;

    @NotBlank(message = "没有填写出发地")
    private String originAdd;

    @NotBlank(message = "没有填写目标地")
    private String desAdd;
}
