package io.gitee.kewen.yuce.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.common.model.dto.req.TravelDeleteReq;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelTenInfoResp;
import io.gitee.kewen.yuce.common.model.entity.TravelTable;

import java.util.List;

/**
 * (TravelTable)表服务接口
 *
 * @author makejava
 * @since 2024-03-24 14:44:35
 */
public interface TravelTableService extends IService<TravelTable> {

    List<TravelTenInfoResp> getTenInfo();

    List<TravelTable> selectListByUserId(Long userId);

}

