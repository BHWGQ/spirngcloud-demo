package io.gitee.kewen.yuce.beattractions.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.beattractions.dto.req.AttCollectHobbyInsertReq;
import io.gitee.kewen.yuce.beattractions.dto.resp.AttCollectHobbyResp;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import io.gitee.kewen.yuce.common.model.entity.HobbyTable;

import java.util.List;

/**
 * (HobbyTable)表服务接口
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
public interface HobbyTableService extends IService<HobbyTable> {

    List<AttTableSingle> selectByUserId(Long userId);

    AttCollectHobbyResp insertByReq(AttCollectHobbyInsertReq req);

    AttCollectHobbyResp DeleteByReq(AttCollectHobbyInsertReq req);

    int queryCollectCounts(Integer attId);
}

