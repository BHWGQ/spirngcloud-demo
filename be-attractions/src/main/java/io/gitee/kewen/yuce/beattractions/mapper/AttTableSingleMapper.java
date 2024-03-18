package io.gitee.kewen.yuce.beattractions.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.gitee.kewen.yuce.beattractions.dto.resp.AttHomePageQueryTen;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (AttTableSingle)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Mapper
public interface AttTableSingleMapper extends BaseMapper<AttTableSingle> {

//    @Select("SELECT * FROM att_table_single as t1 WHERE t1.id>=(RAND()*(SELECT MAX(id)FROM att_table_single)) Limit 10")
    @Select("SELECT id, att_name, att_address, score FROM att_table_single as t1 ORDER BY RAND() LIMIT 10")
    List<AttHomePageQueryTen> getRandomRecords();


}

