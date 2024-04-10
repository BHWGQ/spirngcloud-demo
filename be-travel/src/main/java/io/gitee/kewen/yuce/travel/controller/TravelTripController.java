package io.gitee.kewen.yuce.travel.controller;

import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.service.TripTableService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wgq
 * @version 1.0
 * @data 2024/4/10 19:25
 */
@RestController
@RequestMapping("/trip")
public class TravelTripController {
    @Resource
    private TripTableService tripTableService;

    @PostMapping("/insertTrip")
    public Result<String> messageTell (){
        return null;
    }
}
