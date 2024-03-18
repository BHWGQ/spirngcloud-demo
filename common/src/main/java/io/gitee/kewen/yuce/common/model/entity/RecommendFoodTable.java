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
 * (RecommendFoodTable)表实体类
 *
 * @author makejava
 * @since 2024-03-05 11:42:50
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("recommend_food_table")
public class RecommendFoodTable implements Serializable {

    private static final long serialVersionUID = -33497560284096647L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("address_id")
    private Integer addressId;

    @TableField("rest_name")
    private String restName;

    @TableField("specific_address")
    private String specificAddress;

    @TableField("introduce")
    private String introduce;

    @TableField("recommend_food")
    private String recommendFood;

    @TableField("per_price")
    private Object perPrice;

    @TableField("phone_number")
    private Long phoneNumber;

    @TableField("rest_id")
    private Integer restId;


}

