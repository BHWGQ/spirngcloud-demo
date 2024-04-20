package io.gitee.kewen.yuce.travel.controller;

import cn.zrgzs.chatglm.model.ChatCompletionRequest;
import cn.zrgzs.chatglm.model.ChatCompletionResponse;
import cn.zrgzs.chatglm.model.Model;
import cn.zrgzs.chatglm.session.OpenAiSession;
import com.alibaba.fastjson.JSON;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.dto.req.TripInsertReq;
import io.gitee.kewen.yuce.travel.service.TripTableService;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/10 19:25
 */
@RestController
@Validated
@RequestMapping("/trip")
public class TravelTripController {
    @Resource
    private TripTableService tripTableService;


    @PostMapping("/insertTrip")
    public Result<String> messageTell(@Valid @RequestBody TripInsertReq req) {
        Boolean result = tripTableService.insertTrip(req);
        if (!result) {
            return Result.fail(null);
        }
        return Result.success(null);
    }

    @Resource
    private OpenAiSession openAiSession;

    @GetMapping("/chat")
    public ResponseBodyEmitter message1Tell(HttpServletResponse response , @RequestParam("message") String message) {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest();

        chatCompletionRequest.setModel(Model.GLM_3_Turbo);
        chatCompletionRequest.setMessage(new ArrayList<ChatCompletionRequest.Prompt>() {
            {
                add(ChatCompletionRequest.Prompt.builder()
                        .role("user")
                        .content(message)
                        .build());
            }
        });
        openAiSession.completions(chatCompletionRequest, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, @Nullable String id, @Nullable String type, String data) {
                if ("[DONE]".equals(data)) {
//                    logger.info("[输出结束] Tokens {}", JSON.toJSONString(data));
                    return;
                }
                ChatCompletionResponse response = JSON.parseObject(data, ChatCompletionResponse.class);
                List<ChatCompletionResponse.Choice> choices = response.getChoices();
                for (ChatCompletionResponse.Choice choice : choices) {
                    if ("stop".equals(choice.getFinishReason())) {
                        emitter.complete();
                        break;
                    }
                    try {
                        emitter.send(choice.getMessage().getContent());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(EventSource eventSource, @Nullable Throwable t, @Nullable Response response) {
                super.onFailure(eventSource, t, response);
            }
        });
        return emitter;
    }
}
