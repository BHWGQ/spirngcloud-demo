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
 * (HotelPicture)表实体类
 *
 * @author makejava
 * @since 2024-03-10 12:20:46
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("hotel_picture")
public class HotelPicture implements Serializable {

    private static final long serialVersionUID = 957271608083021844L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("add_id")
    private Integer addId;

    @TableField("hotel_picture")
    private String hotelPicture;

    @TableField("hotel_id")
    private Integer hotelId;


}

