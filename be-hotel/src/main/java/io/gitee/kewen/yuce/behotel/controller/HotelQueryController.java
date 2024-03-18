package io.gitee.kewen.yuce.behotel.controller;

import io.gitee.kewen.yuce.common.model.dto.resp.HotelRecommendResp;
import io.gitee.kewen.yuce.common.model.dto.resp.HotelRecommendThreeResp;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.service.HotelPictureService;
import io.gitee.kewen.yuce.common.service.HotelTableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hotelQuery")
public class HotelQueryController {
    @Resource
    private HotelPictureService hotelPictureService;

    @Resource
    private HotelTableService hotelTableService;

    @GetMapping("/recHotel")
    public Result<List<HotelRecommendThreeResp>> threeRespResult (@RequestParam("addressId") Integer addressId){
        List<HotelRecommendResp> hotelRecommendResp = hotelTableService.getThree(addressId);
        List<HotelRecommendThreeResp> hotelRecommendThreeResps = new ArrayList<>();
        for (HotelRecommendResp item: hotelRecommendResp){
            String pictureUrl = hotelPictureService.getPicture(item.getHotelId());
            HotelRecommendThreeResp hotelRecommendThreeResp = new HotelRecommendThreeResp(pictureUrl,item.getAddressId(),item.getHotelName(),item.getHotelAddress(),item.getHotelId());
            hotelRecommendThreeResps.add(hotelRecommendThreeResp);
        }
        return Result.success(hotelRecommendThreeResps);
    }
}
