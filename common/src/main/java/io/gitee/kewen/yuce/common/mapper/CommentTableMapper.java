package io.gitee.kewen.yuce.common.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.gitee.kewen.yuce.common.model.entity.CommentTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (CommentTable)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Mapper
public interface CommentTableMapper extends BaseMapper<CommentTable> {

    @Select("SELECT * FROM comment_table WHERE user_id = #{userId} GROUP BY att_id")
    List<CommentTable> selectAttByUserId(Long userId);
}

