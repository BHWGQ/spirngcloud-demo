package io.gitee.kewen.yuce.beattractions.dto.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttHomePageQueryTen implements Serializable {
    private Integer Id;

    private String attName;

    private String attAddress;

    private Object score;
}
