package io.gitee.kewen.yuce.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodComment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (RecommendFoodComment)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-30 19:23:36
 */
@Mapper
public interface RecommendFoodCommentMapper extends BaseMapper<RecommendFoodComment> {

    @Select("SELECT * FROM recommend_food_comment WHERE user_id = #{userId} GROUP BY rest_id")
    List<RecommendFoodComment> selectByUserId(Long userId);
}

