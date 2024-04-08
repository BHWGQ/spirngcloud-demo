package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.TravelTableMapper;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelTenInfoResp;
import io.gitee.kewen.yuce.common.model.entity.TravelTable;
import io.gitee.kewen.yuce.common.service.TravelTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TravelTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-24 14:44:35
 */
@Service("travelTableService")
public class TravelTableServiceImpl extends ServiceImpl<TravelTableMapper, TravelTable> implements TravelTableService {

    @Resource
    private TravelTableMapper travelTableMapper;

    @Override
    public List<TravelTenInfoResp> getTenInfo() {
        List<TravelTenInfoResp> travelTenInfoResps = travelTableMapper.selectTenList();
        if (CollectionUtil.isEmpty(travelTenInfoResps)){
            throw new RuntimeException("出错啦");
        }
        return travelTenInfoResps;
    }

    @Override
    public List<TravelTable> selectListByUserId(Long userId) {
        LambdaQueryWrapper<TravelTable> wrapper = new QueryWrapper<TravelTable>().lambda()
                .eq(TravelTable::getUserId,userId);
        List<TravelTable> travelTables = travelTableMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(travelTables)){
            return null;
        }
        return travelTables;
    }
}

