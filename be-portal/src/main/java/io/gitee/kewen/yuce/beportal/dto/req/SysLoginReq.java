package io.gitee.kewen.yuce.beportal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLoginReq {
    @NotNull(message = "用户账号不能为空")
    private Long userId;

    @NotBlank(message = "密码不能为空")
    private String password;
}
