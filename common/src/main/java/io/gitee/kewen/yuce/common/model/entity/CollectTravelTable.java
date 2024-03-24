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
 * (CollectTravelTable)表实体类
 *
 * @author makejava
 * @since 2024-03-24 14:44:33
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("collect_travel_table")
public class CollectTravelTable implements Serializable {

    private static final long serialVersionUID = -79065113992978383L;

    @TableId(type = IdType.AUTO)
    private Integer id;


    @TableField("user_id")
    private Long userId;

    @TableField("travel_id")
    private Integer travelId;


}

