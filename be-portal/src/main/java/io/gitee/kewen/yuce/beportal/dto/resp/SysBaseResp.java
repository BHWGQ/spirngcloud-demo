package io.gitee.kewen.yuce.beportal.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysBaseResp {
    private Long userId;

    private String userName;

    private Long phoneNumber;

    private LocalDateTime createTime;

    private String email;
}
