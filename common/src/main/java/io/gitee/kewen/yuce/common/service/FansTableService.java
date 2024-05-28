package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.dto.resp.UserSubscribeResp;
import io.gitee.kewen.yuce.common.model.entity.FansTable;

import java.util.List;

/**
 * (FansTable)表服务接口
 *
 * @author makejava
 * @since 2024-05-28 09:04:48
 */
public interface FansTableService extends IService<FansTable> {

    int getUserFansCounts(Long userId);

    int getUserSubscribeCounts(Long userId);

    List<FansTable> userSubscribeQuery(Long userId);

    List<FansTable> userFansQuery(Long userId);
}

