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
 * (HobbyTable)表实体类
 *
 * @author makejava
 * @since 2024-03-05 11:42:50
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("hobby_table")
public class HobbyTable implements Serializable {

    private static final long serialVersionUID = -93142434361651778L;

    @TableId(type = IdType.AUTO)
    private Integer id;


    @TableField("user_id")
    private Long userId;


    @TableField("att_id")
    private Integer attId;


}

