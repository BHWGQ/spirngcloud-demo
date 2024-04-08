package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.dto.req.CommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.Att_user;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.model.entity.CommentTable;

import java.util.List;

/**
 * (CommentTable)表服务接口
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
public interface CommentTableService extends IService<CommentTable> {

    CommentInsertResp insert(CommentInsertReq req);

    List<Att_user> queryByUserId(Long userId);
}

