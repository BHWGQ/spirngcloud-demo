package io.gitee.kewen.yuce.common.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/10/25 16:28
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("pa_info")
public class PaInfo {
    @TableField(value = "id")
    private Long id;

    @TableField(value = "title")
    private String title;

    @TableField(value = "link")
    private String link;
}
