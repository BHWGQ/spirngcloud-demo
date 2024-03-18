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
 * (AttTableSingle)表实体类
 *
 * @author makejava
 * @since 2024-03-05 11:42:50
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("att_table_single")
public class AttTableSingle implements Serializable {

    private static final long serialVersionUID = -49503755549966729L;

    @TableId(type = IdType.AUTO)
    private Integer id;


    /**
     * 景点名称
     */
    @TableField("att_name")
    private String attName;


    /**
     * 地点
     */
    @TableField("att_address")
    private String attAddress;


    @TableField("address_id")
    private Integer addressId;


    /**
     * 开放时间描述
     */
    @TableField("open_time")
    private String openTime;


    @TableField("phone_number")
    private Long phoneNumber;


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
     * 分布
     */
    @TableField("distribution")
    private String distribution;


    /**
     * 政策
     */
    @TableField("policy")
    private String policy;


    /**
     * 设施
     */
    @TableField("facilities")
    private String facilities;


}

