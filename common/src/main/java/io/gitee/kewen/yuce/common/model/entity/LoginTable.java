package io.gitee.kewen.yuce.common.model.entity;

import java.time.LocalDateTime;

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
 * (LoginTable)表实体类
 *
 * @author makejava
 * @since 2024-02-29 12:04:50
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("login_table")
public class LoginTable implements Serializable {

    private static final long serialVersionUID = -90906585834110731L;

    @TableId(value = "user_id")
    private Long userId;

    @TableField("user_name")
    private String userName;


    @TableField("password")
    private String password;

    @TableField("phone_number")
    private Long phoneNumber;

    @TableField("roler")
    private String roler;

    @TableField("status")
    private Integer status;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("signature")
    private String signature;

    @TableField("picture_person")
    private String picture;

    @TableField("user_email")
    private String email;
}

