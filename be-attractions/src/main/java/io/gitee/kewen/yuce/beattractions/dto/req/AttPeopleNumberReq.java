package io.gitee.kewen.yuce.beattractions.dto.req;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


/**
 * @author wgq
 * @version 1.0
 * @data 2024/5/14 14:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AttPeopleNumberReq {
    @NotBlank(message = "请输入景点名称")
    private String attName;

    @NotNull(message = "请输入查询日期")
    @DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss zzz yyyy")
    private LocalDate queryTime;
}
