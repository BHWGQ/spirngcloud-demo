package io.gitee.kewen.yuce.beattractions.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttHomePageQueryTen {
    private Integer Id;

    private String attName;

    private String attAddress;

    private Object score;
}
