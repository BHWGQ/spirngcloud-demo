package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.SysLoginMapper;
import io.gitee.kewen.yuce.common.mapper.TravelCommentMapper;
import io.gitee.kewen.yuce.common.model.dto.req.TravelCommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.model.entity.HotelComment;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;
import io.gitee.kewen.yuce.common.model.entity.TravelComment;
import io.gitee.kewen.yuce.common.service.TravelCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * (TravelComment)表服务实现类
 *
 * @author makejava
 * @since 2024-03-30 19:53:34
 */
@Service("travelCommentService")
public class TravelCommentServiceImpl extends ServiceImpl<TravelCommentMapper, TravelComment> implements TravelCommentService {
    @Resource
    private SysLoginMapper loginMapper;

    @Resource
    private TravelCommentMapper travelCommentMapper;

    @Override
    public CommentInsertResp insert(TravelCommentInsertReq req) {
        LambdaQueryWrapper<LoginTable> lambdaQueryWrapper = new QueryWrapper<LoginTable>().lambda()
                .eq(LoginTable::getUserId,req.getUserId())
                .eq(LoginTable::getStatus,0);
        LoginTable loginTable = loginMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtil.isNull(loginTable)){
            throw new RuntimeException("您已被暂时封禁，无法发言");
        }
        LocalDateTime current = LocalDateTime.now();
        TravelComment travelComment = new TravelComment();
        travelComment.setTravelId(req.getTravelId());
        travelComment.setUserName(req.getUserName());
        travelComment.setUserId(req.getUserId());
        travelComment.setContent(req.getContent());
        travelComment.setReply(req.getReply());
        travelComment.setCreateTime(current);
        int affectRows = travelCommentMapper.insert(travelComment);
        if (affectRows == 0){
            throw new RuntimeException("评论失败，正在抢修!");
        }
        return new CommentInsertResp(req.getUserName());
    }
}

