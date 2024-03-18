package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.dto.resp.FoodRecommendResp;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodTable;

import java.util.List;

/**
 * (RecommendFoodTable)表服务接口
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
public interface RecommendFoodTableService extends IService<RecommendFoodTable> {

    List<FoodRecommendResp> getThree(Integer addressId);

    List<FoodRecommendResp> getThreeresp();

    RecommendFoodTable getByRestId(Integer restId);
}

