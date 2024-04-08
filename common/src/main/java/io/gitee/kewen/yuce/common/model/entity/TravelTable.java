package io.gitee.kewen.yuce.common.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.*;

/**
 * (TravelTable)表实体类
 *
 * @author makejava
 * @since 2024-03-24 14:44:35
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("travel_table")
public class TravelTable implements Serializable {

    private static final long serialVersionUID = -55462690827912991L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Long userId;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("att_picture")
    private String attPicture;

    @TableField("introduce")
    private String introduce;

    @TableField("recommend_per")
    private String recommendPer;

    @TableField("travel_days")
    private Integer travelDays;

    @TableField("travel_pays")
    private Object travelPays;

    @TableField("travel_mouth")
    private Integer travelMouth;

    @TableField("att_name")
    private String attName;

    @TableField("address")
    private String address;

}

