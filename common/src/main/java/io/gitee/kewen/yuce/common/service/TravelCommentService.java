package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.dto.req.TravelCommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelOneResp;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelQueryResp;
import io.gitee.kewen.yuce.common.model.entity.TravelComment;

import java.util.List;

/**
 * (TravelComment)表服务接口
 *
 * @author makejava
 * @since 2024-03-30 19:53:34
 */
public interface TravelCommentService extends IService<TravelComment> {

    CommentInsertResp insert(TravelCommentInsertReq req);

    List<TravelOneResp> queryByUserId(Long userId);
}

