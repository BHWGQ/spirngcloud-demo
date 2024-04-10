package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.dto.req.TravelInsertInfoReq;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelResp;
import io.gitee.kewen.yuce.common.model.entity.HobbyTravelTable;

import java.util.List;


/**
 * (HobbyTravelTable)表服务接口
 *
 * @author makejava
 * @since 2024-03-24 14:44:34
 */
public interface HobbyTravelTableService extends IService<HobbyTravelTable> {

    List<HobbyTravelTable> getByUserId(Long userId);

    TravelResp insertHobby(TravelInsertInfoReq req);

    TravelResp deleteHobby(TravelInsertInfoReq req);

    int queryHobbyCounts(Integer travelId);

    Boolean querySature(Long userId, Integer travelId);
}

