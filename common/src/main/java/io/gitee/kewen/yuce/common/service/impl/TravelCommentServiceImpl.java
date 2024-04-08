package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.feign.PortalClient;
import io.gitee.kewen.yuce.common.mapper.SysLoginMapper;
import io.gitee.kewen.yuce.common.mapper.TravelCommentMapper;
import io.gitee.kewen.yuce.common.model.dto.req.TravelCommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelOneResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelQueryResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelUserInfoResp;
import io.gitee.kewen.yuce.common.model.entity.HotelComment;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;
import io.gitee.kewen.yuce.common.model.entity.TravelComment;
import io.gitee.kewen.yuce.common.model.entity.TravelTable;
import io.gitee.kewen.yuce.common.service.TravelCommentService;
import io.gitee.kewen.yuce.common.service.TravelTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Resource
    private TravelTableService travelTableService;

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

    @Override
    public List<TravelOneResp> queryByUserId(Long userId) {
        List<TravelComment> travelComments = travelCommentMapper.selectByUserId(userId);
        if (CollectionUtil.isEmpty(travelComments)){
            return null;
        }
        List<TravelOneResp> travelQueryResps = new ArrayList<>();
        for (TravelComment item : travelComments){
            TravelTable travelTable = travelTableService.getById(item.getTravelId());
            TravelOneResp travelOneResp = new TravelOneResp(travelTable.getId(),travelTable.getCreateTime(),travelTable.getAttName(),travelTable.getAttPicture(),travelTable.getAddress(),travelTable.getIntroduce());
            travelQueryResps.add(travelOneResp);
        }
        return travelQueryResps;
    }
}

