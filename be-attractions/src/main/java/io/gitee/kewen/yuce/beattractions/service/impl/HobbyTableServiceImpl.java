package io.gitee.kewen.yuce.beattractions.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.beattractions.mapper.AttTableSingleMapper;
import io.gitee.kewen.yuce.common.mapper.HobbyTableMapper;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import io.gitee.kewen.yuce.common.model.entity.CollectTable;
import io.gitee.kewen.yuce.common.model.entity.HobbyTable;
import io.gitee.kewen.yuce.beattractions.service.HobbyTableService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (HobbyTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("hobbyTableService")
public class HobbyTableServiceImpl extends ServiceImpl<HobbyTableMapper, HobbyTable> implements HobbyTableService {

    @Resource
    private HobbyTableMapper hobbyTableMapper;

    @Resource
    private AttTableSingleMapper attTableSingleMapper;

    @Override
    public List<AttTableSingle> selectByUserId(Long userId) {
        LambdaQueryWrapper<HobbyTable> wrapper = new QueryWrapper<HobbyTable>().lambda()
                .eq(HobbyTable::getUserId,userId);
        List<HobbyTable> hobbyTables = hobbyTableMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(hobbyTables)){
            throw new RuntimeException("该用户暂时没有收藏任何景点信息");
        }
        List<AttTableSingle> attTableSingles = new ArrayList<>();
        for (HobbyTable item : hobbyTables){
            AttTableSingle attTableSingle = attTableSingleMapper.selectById(item.getAttId());
            attTableSingles.add(attTableSingle);
        }
        return attTableSingles;
    }
}

