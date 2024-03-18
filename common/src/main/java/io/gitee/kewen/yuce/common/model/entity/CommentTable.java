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
 * (CommentTable)表实体类
 *
 * @author makejava
 * @since 2024-03-05 11:42:50
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("comment_table")
public class CommentTable implements Serializable {

    private static final long serialVersionUID = 327018974581827141L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("att_id")
    private Integer attId;

    @TableField("user_name")
    private String userName;

    @TableField("user_id")
    private Long userId;

    @TableField("content")
    private String content;

    @TableField("reply")
    private String reply;


}

