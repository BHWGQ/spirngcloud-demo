package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.gitee.kewen.yuce.common.mapper.CollectTravelTableMapper;
import io.gitee.kewen.yuce.common.model.entity.CollectTravelTable;
import io.gitee.kewen.yuce.common.service.CollectTravelTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (CollectTravelTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-24 14:44:34
 */
@Service("collectTravelTableService")
public class CollectTravelTableServiceImpl extends ServiceImpl<CollectTravelTableMapper, CollectTravelTable> implements CollectTravelTableService {

    @Resource
    private CollectTravelTableMapper collectTravelTableMapper;

    @Override
    public List<CollectTravelTable> getByUserId(Long userId) {
        LambdaQueryWrapper<CollectTravelTable> wrapper = new QueryWrapper<CollectTravelTable>().lambda()
                .eq(CollectTravelTable::getUserId,userId);
        List<CollectTravelTable> collectTravelTables = collectTravelTableMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(collectTravelTables)){
            throw new RuntimeException("该用户暂无收藏的游记");
        }
        return collectTravelTables;
    }
}

