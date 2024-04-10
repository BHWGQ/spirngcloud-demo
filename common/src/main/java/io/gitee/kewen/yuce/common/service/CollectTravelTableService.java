package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.dto.req.TravelInsertInfoReq;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelResp;
import io.gitee.kewen.yuce.common.model.entity.CollectTravelTable;

import java.util.List;

/**
 * (CollectTravelTable)表服务接口
 *
 * @author makejava
 * @since 2024-03-24 14:44:34
 */
public interface CollectTravelTableService extends IService<CollectTravelTable> {

    List<CollectTravelTable> getByUserId(Long userId);

    TravelResp insertCollectByReq(TravelInsertInfoReq req);

    TravelResp deleteCollectByReq(TravelInsertInfoReq req);

    int queryCollectCounts(Integer travelId);

    Boolean querySature(Long userId, Integer travelId);
}

