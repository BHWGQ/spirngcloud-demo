package io.gitee.kewen.yuce.common.model.entity;

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
 * (HobbyTravelTable)表实体类
 *
 * @author makejava
 * @since 2024-03-24 14:44:34
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("hobby_travel_table")
public class HobbyTravelTable implements Serializable {

    private static final long serialVersionUID = -98969265724596673L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Long userId;

    @TableField("travel_id")
    private Integer travelId;


}

