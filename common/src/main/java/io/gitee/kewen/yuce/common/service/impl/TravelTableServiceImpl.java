package io.gitee.kewen.yuce.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.TravelTableMapper;
import io.gitee.kewen.yuce.common.model.entity.TravelTable;
import io.gitee.kewen.yuce.common.service.TravelTableService;
import org.springframework.stereotype.Service;

/**
 * (TravelTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-24 14:44:35
 */
@Service("travelTableService")
public class TravelTableServiceImpl extends ServiceImpl<TravelTableMapper, TravelTable> implements TravelTableService {

}

