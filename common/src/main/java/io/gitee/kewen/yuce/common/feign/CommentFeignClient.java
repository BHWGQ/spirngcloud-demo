package io.gitee.kewen.yuce.common.feign;

import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentTableQueryResp;
import io.gitee.kewen.yuce.common.model.entity.CommentTable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "comment-service" , path = "/comment/com")
public interface CommentFeignClient {
    @GetMapping("/query")
    Result<List<CommentTable>> commentTableResult (@RequestParam("Id") Integer Id);

}
