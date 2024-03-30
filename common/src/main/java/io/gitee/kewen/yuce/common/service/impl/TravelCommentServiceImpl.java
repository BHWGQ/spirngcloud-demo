package io.gitee.kewen.yuce.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.TravelCommentMapper;
import io.gitee.kewen.yuce.common.model.entity.TravelComment;
import io.gitee.kewen.yuce.common.service.TravelCommentService;
import org.springframework.stereotype.Service;

/**
 * (TravelComment)表服务实现类
 *
 * @author makejava
 * @since 2024-03-30 19:53:34
 */
@Service("travelCommentService")
public class TravelCommentServiceImpl extends ServiceImpl<TravelCommentMapper, TravelComment> implements TravelCommentService {

}

