package io.gitee.kewen.yuce.common.model.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.*;

/**
 * (TripTable)表实体类
 *
 * @author makejava
 * @since 2024-03-05 11:42:50
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("trip_table")
public class TripTable implements Serializable {

    private static final long serialVersionUID = 750469916467946308L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Long userId;

    @TableField("content")
    private String content;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("leave_time")
    private LocalDateTime leaveTime;

    @TableField("origin_add")
    private String originAdd;

    @TableField("des_add")
    private String desAdd;


}

