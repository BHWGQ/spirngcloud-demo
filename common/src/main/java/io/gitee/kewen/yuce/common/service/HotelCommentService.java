package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.dto.req.HotelCommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.model.entity.HotelComment;

/**
 * (HotelComment)表服务接口
 *
 * @author makejava
 * @since 2024-03-30 19:23:36
 */
public interface HotelCommentService extends IService<HotelComment> {

    CommentInsertResp insert(HotelCommentInsertReq req);
}

