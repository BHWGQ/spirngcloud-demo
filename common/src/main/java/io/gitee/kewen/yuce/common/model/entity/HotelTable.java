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
 * (HotelTable)表实体类
 *
 * @author makejava
 * @since 2024-03-05 11:42:50
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("hotel_table")
public class HotelTable implements Serializable {

    private static final long serialVersionUID = 364468856949676217L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("address_id")
    private Integer addressId;

    @TableField("hotel_name")
    private String hotelName;

    @TableField("hotel_address")
    private String hotelAddress;

    @TableField("introduce")
    private String introduce;

    @TableField("phone_number")
    private Long phoneNumber;

    @TableField("hotel_id")
    private Integer hotelId;

}

