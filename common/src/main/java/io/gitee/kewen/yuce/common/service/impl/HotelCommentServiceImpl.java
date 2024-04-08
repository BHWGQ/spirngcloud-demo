package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.HotelCommentMapper;
import io.gitee.kewen.yuce.common.mapper.HotelTableMapper;
import io.gitee.kewen.yuce.common.mapper.SysLoginMapper;
import io.gitee.kewen.yuce.common.model.dto.req.HotelCommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.model.dto.resp.HotelRecommendThreeResp;
import io.gitee.kewen.yuce.common.model.entity.HotelComment;
import io.gitee.kewen.yuce.common.model.entity.HotelTable;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;
import io.gitee.kewen.yuce.common.service.HotelCommentService;
import io.gitee.kewen.yuce.common.service.HotelPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * (HotelComment)表服务实现类
 *
 * @author makejava
 * @since 2024-03-30 19:23:37
 */
@Service("hotelCommentService")
public class HotelCommentServiceImpl extends ServiceImpl<HotelCommentMapper, HotelComment> implements HotelCommentService {
    @Resource
    private SysLoginMapper loginMapper;

    @Resource
    private HotelCommentMapper hotelCommentMapper;

    @Resource
    private HotelPictureService hotelPictureService;

    @Resource
    private HotelTableMapper hotelTableMapper;

    @Override
    public CommentInsertResp insert(HotelCommentInsertReq req) {
        LambdaQueryWrapper<LoginTable> lambdaQueryWrapper = new QueryWrapper<LoginTable>().lambda()
                .eq(LoginTable::getUserId,req.getUserId())
                .eq(LoginTable::getStatus,0);
        LoginTable loginTable = loginMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtil.isNull(loginTable)){
            throw new RuntimeException("您已被暂时封禁，无法发言");
        }
        LocalDateTime current = LocalDateTime.now();
        HotelComment hotelComment = new HotelComment();
        hotelComment.setHotelId(req.getHotelId());
        hotelComment.setUserName(req.getUserName());
        hotelComment.setUserId(req.getUserId());
        hotelComment.setContent(req.getContent());
        hotelComment.setReply(req.getReply());
        hotelComment.setCreateTime(current);
        int affectRows = hotelCommentMapper.insert(hotelComment);
        if (affectRows == 0){
            throw new RuntimeException("评论失败，正在抢修!");
        }
        return new CommentInsertResp(req.getUserName());
    }

    @Override
    public List<HotelRecommendThreeResp> queryByUserId(Long userId) {
        List<HotelComment> hotelComments = hotelCommentMapper.selectByUserId(userId);
        if (CollectionUtil.isEmpty(hotelComments)){
            return null;
        }
        List<HotelRecommendThreeResp> hotelRecommendThreeResps = new ArrayList<>();
        for (HotelComment item : hotelComments){
            int  hotelId = item.getHotelId();
            String picture = hotelPictureService.getPicture(hotelId);
            LambdaQueryWrapper<HotelTable> wrapper = new QueryWrapper<HotelTable>().lambda()
                    .eq(HotelTable::getHotelId,hotelId);
            HotelTable hotelTable = hotelTableMapper.selectOne(wrapper);
            HotelRecommendThreeResp hotelRecommendThreeResp = new HotelRecommendThreeResp(picture,hotelTable.getAddressId(),hotelTable.getHotelName(),hotelTable.getHotelAddress(),hotelId);
            hotelRecommendThreeResps.add(hotelRecommendThreeResp);
        }
        return hotelRecommendThreeResps;
    }
}

