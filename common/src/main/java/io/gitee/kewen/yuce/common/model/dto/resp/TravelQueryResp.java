package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TravelQueryResp {
    private Integer id;

    private String userName;

    private LocalDateTime createTime;

    private String attName;

    private String attPicture;

    private String userPicture;

    private String address;

    private String intorduce;
}
