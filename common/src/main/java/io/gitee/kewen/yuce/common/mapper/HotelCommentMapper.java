package io.gitee.kewen.yuce.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.gitee.kewen.yuce.common.model.entity.HotelComment;

import org.apache.ibatis.annotations.Mapper;

/**
 * (HotelComment)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-30 19:23:37
 */
@Mapper
public interface HotelCommentMapper extends BaseMapper<HotelComment> {

}

