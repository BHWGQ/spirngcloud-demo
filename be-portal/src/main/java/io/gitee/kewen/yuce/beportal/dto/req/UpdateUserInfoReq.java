package io.gitee.kewen.yuce.beportal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInfoReq {
    private String userName;

    private Long userId;

    private String signature;

    private String email;
}
