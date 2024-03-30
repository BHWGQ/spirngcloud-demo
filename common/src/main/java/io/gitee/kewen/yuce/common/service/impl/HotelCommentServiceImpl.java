package io.gitee.kewen.yuce.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.HotelCommentMapper;
import io.gitee.kewen.yuce.common.model.entity.HotelComment;
import io.gitee.kewen.yuce.common.service.HotelCommentService;
import org.springframework.stereotype.Service;

/**
 * (HotelComment)表服务实现类
 *
 * @author makejava
 * @since 2024-03-30 19:23:37
 */
@Service("hotelCommentService")
public class HotelCommentServiceImpl extends ServiceImpl<HotelCommentMapper, HotelComment> implements HotelCommentService {

}

