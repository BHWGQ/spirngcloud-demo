package io.gitee.kewen.yuce.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.gitee.kewen.yuce.common.model.dto.resp.FoodRecommendResp;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (RecommendFoodTable)表数据库访问层
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Mapper
public interface RecommendFoodTableMapper extends BaseMapper<RecommendFoodTable> {

    @Select("SELECT * FROM recommend_food_table WHERE address_id = #{addressId} ORDER BY RAND() LIMIT 3")
    List<RecommendFoodTable> selectRandomRest(@Param("addressId") Integer addressId);

    @Select("SELECT * FROM recommend_food_table ORDER BY RAND() LIMIT 6")
    List<RecommendFoodTable> selectRandom();

}

