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
 * (AttPicture)表实体类
 *
 * @author makejava
 * @since 2024-03-05 11:42:50
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("att_picture")
public class AttPicture implements Serializable {

    private static final long serialVersionUID = 137776001115446824L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("att_id")
    private Integer attId;

    @TableField("att_picture")
    private String attPicture;


}

