package io.gitee.kewen.yuce.common.model.dto.req;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/8 11:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WriteTravelReq {
    private Long userId;

    private String introduce;

    private String recommendPer;

    private Integer travelDays;

    private Object travelPays;

    private Integer travelMouth;

    private String attName;

    private String address;
}
