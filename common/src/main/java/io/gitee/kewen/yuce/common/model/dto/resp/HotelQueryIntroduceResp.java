package io.gitee.kewen.yuce.common.model.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelQueryIntroduceResp {
    private String pictureUrl;

    private String hotelName;

    private String hotelAddress;

    private String introduce;

    private Long phoneNumber;

    private Integer hotelId;
}
