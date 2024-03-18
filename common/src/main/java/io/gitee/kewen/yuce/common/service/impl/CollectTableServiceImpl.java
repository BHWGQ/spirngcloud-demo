package io.gitee.kewen.yuce.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.CollectTableMapper;
import io.gitee.kewen.yuce.common.model.entity.CollectTable;
import io.gitee.kewen.yuce.common.service.CollectTableService;
import org.springframework.stereotype.Service;

/**
 * (CollectTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("collectTableService")
public class CollectTableServiceImpl extends ServiceImpl<CollectTableMapper, CollectTable> implements CollectTableService {

}

