package io.gitee.kewen.yuce.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.gitee.kewen.yuce.common.model.entity.HotelTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (HotelTable)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Mapper
public interface HotelTableMapper extends BaseMapper<HotelTable> {
    @Select("SELECT * FROM hotel_table WHERE address_id = #{addressId} ORDER BY RAND() LIMIT 3")
    List<HotelTable> selectRandomHotels(@Param("addressId") Integer addressId);

    @Select("SELECT * FROM hotel_table WHERE address_id = #{addressId} AND hotel_id != #{hotelId} ORDER BY RAND() LIMIT 3")
    List<HotelTable> selectExceptSelf(@Param("addressId") Integer addressId, @Param("hotelId") Integer hotelId);
}

