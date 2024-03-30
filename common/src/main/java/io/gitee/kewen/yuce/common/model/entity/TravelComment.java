package io.gitee.kewen.yuce.common.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.EqualsAndHashCode;

/**
 * (TravelComment)表实体类
 *
 * @author makejava
 * @since 2024-03-30 19:53:33
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("travel_comment")
public class TravelComment implements Serializable {

    private static final long serialVersionUID = 150440009859439228L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("travel_id")
    private Integer travelId;

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

