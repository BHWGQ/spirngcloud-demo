package io.gitee.kewen.yuce.beportal.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/4 14:39
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysLoginUpdateResp {
    private SysBaseResp sysBaseResp;

    private String signature;

    private String picture;
}
