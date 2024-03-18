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
 * (AttTableMore)表实体类
 *
 * @author makejava
 * @since 2024-03-05 11:42:50
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("att_table_more")
public class AttTableMore implements Serializable {

    private static final long serialVersionUID = 765262191137193948L;

    @TableId(type = IdType.AUTO)
    private Integer id;


    /**
     * 路线
     */
    @TableField("att_route")
    private String attRoute;


    /**
     * 评分
     */
    @TableField("score")
    private Object score;


    /**
     * 介绍
     */
    @TableField("introduce")
    private String introduce;


    /**
     * 设施
     */
    @TableField("facilities")
    private String facilities;


    @TableField("address_id")
    private Integer addressId;


}

