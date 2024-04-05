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
 * (TravelPictureTable)表实体类
 *
 * @author makejava
 * @since 2024-03-24 14:44:34
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("travel_picture_table")
public class TravelPictureTable implements Serializable {

    private static final long serialVersionUID = 389669163776105911L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("travel_id")
    private Integer travelId;

    @TableField("travel_picture")
    private String travelPicture;


}

