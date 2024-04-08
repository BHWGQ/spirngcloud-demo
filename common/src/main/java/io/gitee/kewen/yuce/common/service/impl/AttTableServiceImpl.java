package io.gitee.kewen.yuce.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.AttTableMapper;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import io.gitee.kewen.yuce.common.service.AttTableService;
import org.springframework.stereotype.Service;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/8 14:47
 */
@Service
public class AttTableServiceImpl extends ServiceImpl<AttTableMapper, AttTableSingle> implements AttTableService {
}
