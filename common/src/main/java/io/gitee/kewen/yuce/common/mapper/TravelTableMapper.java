package io.gitee.kewen.yuce.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.gitee.kewen.yuce.common.model.dto.resp.TravelTenInfoResp;
import io.gitee.kewen.yuce.common.model.entity.TravelTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (TravelTable)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-24 14:44:35
 */
@Mapper
public interface TravelTableMapper extends BaseMapper<TravelTable> {

    @Select("SELECT id, create_time, user_id ,att_picture, att_name FROM travel_table as t1 ORDER BY RAND() LIMIT 10")
    List<TravelTenInfoResp> selectTenList();
}

