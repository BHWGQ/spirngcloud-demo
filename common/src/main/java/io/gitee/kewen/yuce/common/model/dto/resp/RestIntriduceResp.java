package io.gitee.kewen.yuce.common.model.dto.resp;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestIntriduceResp {
    private String pictureUrl;

    private String restName;

    private String specificAddress;

    private String introduce;

    private String recommendFood;

    private Object perPrice;

    private Long phoneNumber;

    private Integer restId;
}
