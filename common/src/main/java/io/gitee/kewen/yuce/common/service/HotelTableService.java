package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.dto.resp.HotelRecommendResp;
import io.gitee.kewen.yuce.common.model.entity.HotelTable;

import java.util.List;

/**
 * (HotelTable)表服务接口
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
public interface HotelTableService extends IService<HotelTable> {

    List<HotelRecommendResp> getThree(Integer addressId);

    HotelTable getByHotelId(Integer hotelId);

    List<HotelTable> getListByHotelId(Integer hotelId);
}

