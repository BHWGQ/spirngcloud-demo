package io.gitee.kewen.yuce.beportal.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/5/27 15:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserInfoResp {
    private Long userId;

    private String userName;

    private String signature;

    private String picture;

    private String email;
}
