package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.exception.HotelException.HotelException;
import io.gitee.kewen.yuce.common.mapper.HotelPictureMapper;
import io.gitee.kewen.yuce.common.model.dto.resp.HotelRecommendThreeResp;
import io.gitee.kewen.yuce.common.model.entity.HotelPicture;
import io.gitee.kewen.yuce.common.service.HotelPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (HotelPicture)表服务实现类
 *
 * @author makejava
 * @since 2024-03-10 12:20:46
 */
@Service("hotelPictureService")
public class HotelPictureServiceImpl extends ServiceImpl<HotelPictureMapper, HotelPicture> implements HotelPictureService {

    @Resource
    private HotelPictureMapper hotelPictureMapper;


    @Override
    public String getPicture(Integer hotelId) {
        LambdaQueryWrapper<HotelPicture> lambdaQueryWrapper = new QueryWrapper<HotelPicture>().lambda()
                .eq(HotelPicture::getHotelId,hotelId);
        HotelPicture hotelPicture = hotelPictureMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtil.isNull(hotelPicture)){
            throw HotelException.Add_No_Hotel_picture;
        }
        return hotelPicture.getHotelPicture();
    }
}

