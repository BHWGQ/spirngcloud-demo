package io.gitee.kewen.yuce.beattractions.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import io.gitee.kewen.yuce.common.model.entity.CollectTable;

import java.util.List;

/**
 * (CollectTable)表服务接口
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
public interface CollectTableService extends IService<CollectTable> {

    List<AttTableSingle> selectByUserId(Long userId);
}

