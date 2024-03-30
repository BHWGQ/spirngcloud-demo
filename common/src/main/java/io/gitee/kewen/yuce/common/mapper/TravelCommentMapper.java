package io.gitee.kewen.yuce.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.gitee.kewen.yuce.common.model.entity.TravelComment;

import org.apache.ibatis.annotations.Mapper;

/**
 * (TravelComment)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-30 19:53:34
 */
@Mapper
public interface TravelCommentMapper extends BaseMapper<TravelComment> {

}

