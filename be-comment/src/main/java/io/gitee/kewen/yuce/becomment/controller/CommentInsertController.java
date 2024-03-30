package io.gitee.kewen.yuce.becomment.controller;

import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.req.CommentInsertReq;
import io.gitee.kewen.yuce.common.service.CommentTableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/comment")
public class CommentInsertController {
    @Resource
    private CommentTableService commentTableService;

    @PostMapping("/insert")
    public Result<CommentInsertResp> commentInsertRespResult (@RequestBody CommentInsertReq req){
        CommentInsertResp commentInsertResp = commentTableService.insert(req);
        return Result.success(commentInsertResp);
    }
}
