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
 * (RecommendFoodPicture)表实体类
 *
 * @author makejava
 * @since 2024-03-10 12:20:46
 */

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@TableName("recommend_food_picture")
public class RecommendFoodPicture implements Serializable {

    private static final long serialVersionUID = 950599909864626782L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("add_id")
    private Integer addId;

    @TableField("food_picture")
    private String foodPicture;

    @TableField("rest_id")
    private Integer restId;


}

