package io.gitee.kewen.yuce.beattractions.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.gitee.kewen.yuce.beattractions.dto.resp.AttHomePageQueryTen;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;

import java.util.List;

/**
 * (AttTableSingle)表服务接口
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
public interface AttTableSingleService extends IService<AttTableSingle> {

    List<AttHomePageQueryTen> queryTenInfo();

    List<AttTableSingle> getByAttName(String attName);
}

