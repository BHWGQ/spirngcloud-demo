package io.gitee.kewen.yuce.beportal.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/6 14:13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePictureReq {
    private Integer userId;
    private String oldPicture;
}
