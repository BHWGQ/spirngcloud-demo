package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.bean.Result;
import io.gitee.kewen.yuce.common.exception.PortalException.PortalException;
import io.gitee.kewen.yuce.common.feign.PortalClient;
import io.gitee.kewen.yuce.common.mapper.FansTableMapper;
import io.gitee.kewen.yuce.common.mapper.SysLoginMapper;
import io.gitee.kewen.yuce.common.model.dto.resp.UserSubscribeResp;
import io.gitee.kewen.yuce.common.model.entity.FansTable;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;
import io.gitee.kewen.yuce.common.service.FansTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (FansTable)表服务实现类
 *
 * @author makejava
 * @since 2024-05-28 09:04:48
 */
@Service("fansTableService")
public class FansTableServiceImpl extends ServiceImpl<FansTableMapper, FansTable> implements FansTableService {
    @Resource
    private FansTableMapper mapper;

    @Override
    public int getUserFansCounts(Long userId) {
        LambdaQueryWrapper<FansTable> lambdaQueryWrapper = new QueryWrapper<FansTable>().lambda()
                .eq(FansTable::getUserId,userId);
        return Math.toIntExact(mapper.selectCount(lambdaQueryWrapper));
    }

    @Override
    public int getUserSubscribeCounts(Long userId) {
        LambdaQueryWrapper<FansTable> lambdaQueryWrapper = new QueryWrapper<FansTable>().lambda()
                .eq(FansTable::getFansId,userId);
        return Math.toIntExact(mapper.selectCount(lambdaQueryWrapper));
    }

    @Override
    public List<FansTable> userSubscribeQuery(Long userId) {
        LambdaQueryWrapper<FansTable> lambdaQueryWrapper = new QueryWrapper<FansTable>().lambda()
                .eq(FansTable::getFansId,userId);
        List<FansTable> fansTables = mapper.selectList(lambdaQueryWrapper);
        if (CollectionUtil.isEmpty(fansTables)){
            return null;
        }
        return fansTables;
    }

    @Override
    public List<FansTable> userFansQuery(Long userId) {
        LambdaQueryWrapper<FansTable> lambdaQueryWrapper = new QueryWrapper<FansTable>().lambda()
                .eq(FansTable::getUserId,userId);
        List<FansTable> fansTables = mapper.selectList(lambdaQueryWrapper);
        if (CollectionUtil.isEmpty(fansTables)){
            return null;
        }
        return fansTables;
    }
}

