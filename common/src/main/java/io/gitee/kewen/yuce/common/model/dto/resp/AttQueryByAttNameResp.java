package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttQueryByAttNameResp {
    private String pictureUrl;

    private Integer Id;

    private String attName;

    private String attAddress;

    private Object score;
}
