package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.entity.AttPicture;

/**
 * (AttPicture)表服务接口
 *
 * @author makejava
 * @since 2024-03-05 12:08:01
 */
public interface AttPictureService extends IService<AttPicture> {

    String getByAttId(Integer id);
}

