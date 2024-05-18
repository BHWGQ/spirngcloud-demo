package io.gitee.kewen.yuce.common.model.dto.resp;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TravelQueryResp {
    private Integer id;

    private String userName;

    private LocalDateTime createTime;

    private String attName;

    private String attPicture;

    private String userPicture;

    private String address;

    private String introduce;
}
