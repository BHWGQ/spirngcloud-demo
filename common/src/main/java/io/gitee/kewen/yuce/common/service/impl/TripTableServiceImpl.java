package io.gitee.kewen.yuce.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.TripTableMapper;
import io.gitee.kewen.yuce.common.model.entity.TripTable;
import io.gitee.kewen.yuce.common.service.TripTableService;
import org.springframework.stereotype.Service;

/**
 * (TripTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("tripTableService")
public class TripTableServiceImpl extends ServiceImpl<TripTableMapper, TripTable> implements TripTableService {

}

