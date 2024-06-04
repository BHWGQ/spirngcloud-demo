package io.gitee.kewen.yuce.beportal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/5/29 20:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginOutReq {
    private String token;
}
