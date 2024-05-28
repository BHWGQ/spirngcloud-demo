package io.gitee.kewen.yuce.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.FansTableMapper;
import io.gitee.kewen.yuce.common.model.entity.FansTable;
import io.gitee.kewen.yuce.common.service.FansTableService;
import org.springframework.stereotype.Service;

/**
 * (FansTable)表服务实现类
 *
 * @author makejava
 * @since 2024-05-28 09:04:48
 */
@Service("fansTableService")
public class FansTableServiceImpl extends ServiceImpl<FansTableMapper, FansTable> implements FansTableService {

}

