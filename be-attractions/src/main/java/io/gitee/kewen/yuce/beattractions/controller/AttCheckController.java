package io.gitee.kewen.yuce.beattractions.controller;

import io.gitee.kewen.yuce.beattractions.dto.resp.AttNameResp;
import io.gitee.kewen.yuce.common.Util.HttpUtil;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.consts.AttNameAutoCheck;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URLEncoder;
import java.util.Base64;


/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/22 13:10
 */
@RequestMapping("/check")
@RestController
public class AttCheckController {

    @PostMapping("/getAttName")
    public Result<AttNameResp> attNameRespResult (MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return Result.fail("文件不能为空");
        }
        String imageStr = Base64.getEncoder().encodeToString(file.getBytes());
        String imageParam = URLEncoder.encode(imageStr,"UTF-8");
        String image = "image="+imageParam;
        String result = HttpUtil.post(AttNameAutoCheck.url,AttNameAutoCheck.Access_Token,image);
        return Result.success(new AttNameResp(result));
    }
}
