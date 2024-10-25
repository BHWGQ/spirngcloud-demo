package io.gitee.kewen.yuce.beattractions.controller;

import io.gitee.kewen.yuce.beattractions.dto.req.AttPeopleNumberReq;
import io.gitee.kewen.yuce.beattractions.dto.resp.AttAddressResp;
import io.gitee.kewen.yuce.beattractions.dto.resp.AttNameResp;
import io.gitee.kewen.yuce.beattractions.service.AttTableSingleService;
import io.gitee.kewen.yuce.common.Util.HttpUtil;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.consts.AttNameAutoCheck;
import io.gitee.kewen.yuce.common.model.entity.PaInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.List;


/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/22 13:10
 */
@RequestMapping("/check")
@RestController
@Validated
public class AttCheckController {

    @Resource
    private AttTableSingleService service;

    @PostMapping("/getAttName")
    public Result<AttNameResp> attNameRespResult (MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            return Result.fail("文件不能为空");
        }
        String imageStr = Base64.getEncoder().encodeToString(file.getBytes());
        String imageParam = URLEncoder.encode(imageStr,"UTF-8");
        String image = "image="+imageParam;
        String result = HttpUtil.post(AttNameAutoCheck.url1,AttNameAutoCheck.Access_Token,image);
        return Result.success(new AttNameResp(result));
    }

    @PostMapping("/getPeopleNumber")
    public Result<Integer> personNumberResult (@RequestBody AttPeopleNumberReq req) throws IOException, InterruptedException {
        int forecastResult = service.forecast(req);
        return Result.success(forecastResult);
    }


    @GetMapping("getAddressInfo")
    public Result<List<PaInfo>> result (@RequestParam("addressName") String addressName){
        List<PaInfo> attAddressResps = service.getAddressInfo(addressName);
        return Result.success(attAddressResps);
    }
}
