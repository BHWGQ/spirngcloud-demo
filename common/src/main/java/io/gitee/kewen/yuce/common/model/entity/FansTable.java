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
 * (FansTable)表实体类
 *
 * @author makejava
 * @since 2024-05-28 09:04:48
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("fans_table")
public class FansTable implements Serializable {

    private static final long serialVersionUID = 939001411263278827L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("user_id")
    private Long userId;

    @TableField("fansId")
    private Long fansId;


}

