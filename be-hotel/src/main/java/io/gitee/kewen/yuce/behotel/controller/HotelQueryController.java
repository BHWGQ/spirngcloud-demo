package io.gitee.kewen.yuce.behotel.controller;

import io.gitee.kewen.yuce.common.model.dto.resp.HotelQueryIntroduceResp;
import io.gitee.kewen.yuce.common.model.dto.resp.HotelRecommendResp;
import io.gitee.kewen.yuce.common.model.dto.resp.HotelRecommendThreeResp;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.model.entity.HotelTable;
import io.gitee.kewen.yuce.common.service.HotelPictureService;
import io.gitee.kewen.yuce.common.service.HotelTableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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

    //通过hotelId查看hotel信息以及图片
    @GetMapping("/queryByHotelId")
    public Result<HotelQueryIntroduceResp> hotelQueryIntroduceRespResult (@Valid @RequestParam("hotelId") @NotNull(message = "不能缺失旅馆id") Integer hotelId){
        HotelTable hotelTable = hotelTableService.getByHotelId(hotelId);
        String picture = hotelPictureService.getPicture(hotelTable.getHotelId());
        HotelQueryIntroduceResp hotelQueryIntroduceResp = new HotelQueryIntroduceResp(picture,hotelTable.getHotelName(), hotelTable.getHotelAddress(), hotelTable.getIntroduce(), hotelTable.getPhoneNumber(), hotelTable.getHotelId());
        return Result.success(hotelQueryIntroduceResp);
    }

    //通过hotelId查看当地其他酒店信息
    @GetMapping("/QueryExceptSelf")
    public Result<List<HotelRecommendThreeResp>> listResult (@Valid @RequestParam("hotelId") @NotNull(message = "不能缺失旅馆id") Integer hotelId){
        List<HotelTable> list = hotelTableService.getListByHotelId(hotelId);
        List<HotelRecommendThreeResp> list1 = new ArrayList<>();
        for (HotelTable item : list){
            String picture = hotelPictureService.getPicture(item.getHotelId());
            HotelRecommendThreeResp hotelRecommendThreeResp = new HotelRecommendThreeResp(picture,item.getAddressId(),item.getHotelName(),item.getHotelAddress(),item.getHotelId());
            list1.add(hotelRecommendThreeResp);
        }
        return Result.success(list1);
    }
}
