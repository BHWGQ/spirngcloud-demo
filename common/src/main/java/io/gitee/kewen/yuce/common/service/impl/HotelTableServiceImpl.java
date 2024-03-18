package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.exception.HotelException.HotelException;
import io.gitee.kewen.yuce.common.mapper.HotelTableMapper;
import io.gitee.kewen.yuce.common.model.dto.resp.HotelRecommendResp;
import io.gitee.kewen.yuce.common.model.entity.HotelTable;
import io.gitee.kewen.yuce.common.service.HotelTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (HotelTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("hotelTableService")
public class HotelTableServiceImpl extends ServiceImpl<HotelTableMapper, HotelTable> implements HotelTableService {

    @Resource
    private HotelTableMapper hotelTableMapper;

    @Override
    public List<HotelRecommendResp> getThree(Integer addressId) {
        List<HotelTable> hotelTable = hotelTableMapper.selectRandomHotels(addressId);
        if (ObjectUtil.isEmpty(hotelTable)){
            throw HotelException.Add_No_Hotel;
        }
        List<HotelRecommendResp> hotelRecommendResps = new ArrayList<>();
        for (HotelTable item : hotelTable){
            HotelRecommendResp hotelRecommendResp = new HotelRecommendResp(item.getAddressId(),item.getHotelName(),item.getHotelAddress(),item.getHotelId());
            hotelRecommendResps.add(hotelRecommendResp);
        }
        return hotelRecommendResps;
    }
}

