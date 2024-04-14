package io.gitee.kewen.yuce.travel.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.dto.req.TripInsertReq;
import io.gitee.kewen.yuce.common.model.entity.TripTable;

/**
 * (TripTable)表服务接口
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
public interface TripTableService extends IService<TripTable> {

    Boolean insertTrip(TripInsertReq req);
}

