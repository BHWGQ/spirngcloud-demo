package io.gitee.kewen.yuce.common.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * (RecommendFoodComment)表实体类
 *
 * @author makejava
 * @since 2024-03-30 19:23:36
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("recommend_food_comment")
public class RecommendFoodComment implements Serializable {

    private static final long serialVersionUID = 299377485411639698L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("rest_id")
    private Integer restId;

    @TableField("user_name")
    private String userName;

    @TableField("user_id")
    private Long userId;

    @TableField("content")
    private String content;

    @TableField("reply")
    private Integer reply;

    @TableField("create_time")
    private LocalDateTime createTime;
}

