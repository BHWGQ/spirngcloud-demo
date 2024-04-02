package io.gitee.kewen.yuce.beattractions.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.HobbyTableMapper;
import io.gitee.kewen.yuce.common.model.entity.HobbyTable;
import io.gitee.kewen.yuce.beattractions.service.HobbyTableService;
import org.springframework.stereotype.Service;

/**
 * (HobbyTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("hobbyTableService")
public class HobbyTableServiceImpl extends ServiceImpl<HobbyTableMapper, HobbyTable> implements HobbyTableService {

}

