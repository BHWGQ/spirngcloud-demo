package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.entity.RecommendFoodPicture;

/**
 * (RecommendFoodPicture)表服务接口
 *
 * @author makejava
 * @since 2024-03-10 12:20:46
 */
public interface RecommendFoodPictureService extends IService<RecommendFoodPicture> {

    String getPicture(Integer restId);
}

