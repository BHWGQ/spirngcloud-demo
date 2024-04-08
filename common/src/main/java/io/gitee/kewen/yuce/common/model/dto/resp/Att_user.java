package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/8 13:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Att_user {
    private String pictureUrl;

    private Integer attId;

    private String attName;

    private String attAddress;

    private Object score;
}
