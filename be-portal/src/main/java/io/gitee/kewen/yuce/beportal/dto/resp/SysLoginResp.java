package io.gitee.kewen.yuce.beportal.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysLoginResp {
    private SysBaseResp sysBaseResp;

    private String token;

    private Date expireTime;

    private String signature;

    private String picture;

    private String email;
}
