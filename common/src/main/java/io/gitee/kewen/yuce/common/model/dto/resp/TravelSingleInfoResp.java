package io.gitee.kewen.yuce.common.model.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import io.gitee.kewen.yuce.common.model.entity.TravelTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TravelSingleInfoResp {
    private TravelTable travelTable;

    private String userName;

    private String userPicture;
}
