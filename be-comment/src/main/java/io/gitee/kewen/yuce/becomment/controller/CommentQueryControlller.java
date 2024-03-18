package io.gitee.kewen.yuce.becomment.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.exception.CommentException.CommentException;
import io.gitee.kewen.yuce.common.model.entity.CommentTable;
import io.gitee.kewen.yuce.common.service.CommentTableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/com")
public class CommentQueryControlller {
    @Resource
    private CommentTableService commentTableService;

    @GetMapping("/query")
    public Result<List<CommentTable>> commentTableResult (@RequestParam("Id") Integer Id){
        List<CommentTable> commentTable = commentTableService.list(new LambdaQueryWrapper<CommentTable>().eq(CommentTable::getAttId,Id));
        if (CollectionUtil.isEmpty(commentTable)){
            throw CommentException.Comment_No_Exist;
        }
        return Result.success(commentTable);
    }
}
