package io.gitee.kewen.yuce.beattractions.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttHomePageQueryResp {
    private String pictureUrl;

    private Integer attId;

    private String attName;

    private String attAddress;

    private Object score;
}
