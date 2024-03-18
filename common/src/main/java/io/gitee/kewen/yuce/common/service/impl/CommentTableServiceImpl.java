package io.gitee.kewen.yuce.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.CommentTableMapper;
import io.gitee.kewen.yuce.common.model.entity.CommentTable;
import io.gitee.kewen.yuce.common.service.CommentTableService;
import org.springframework.stereotype.Service;

/**
 * (CommentTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("commentTableService")
public class CommentTableServiceImpl extends ServiceImpl<CommentTableMapper, CommentTable> implements CommentTableService {

}

