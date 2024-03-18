package io.gitee.kewen.yuce.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysLoginMapper extends BaseMapper<LoginTable> {
}
