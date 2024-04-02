package io.gitee.kewen.yuce.beattractions.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.beattractions.mapper.AttTableSingleMapper;
import io.gitee.kewen.yuce.common.mapper.CollectTableMapper;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import io.gitee.kewen.yuce.common.model.entity.CollectTable;
import io.gitee.kewen.yuce.beattractions.service.CollectTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (CollectTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("collectTableService")
public class CollectTableServiceImpl extends ServiceImpl<CollectTableMapper, CollectTable> implements CollectTableService {

    @Resource
    private CollectTableMapper collectTableMapper;

    @Resource
    private AttTableSingleMapper attTableSingleMapper;


    @Override
    public List<AttTableSingle> selectByUserId(Long userId) {
        LambdaQueryWrapper<CollectTable> wrapper = new QueryWrapper<CollectTable>().lambda()
                .eq(CollectTable::getUserId,userId);
        List<CollectTable> collectTables = collectTableMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(collectTables)){
            throw new RuntimeException("该用户暂时没有收藏任何景点信息");
        }
        List<AttTableSingle> attTableSingles = new ArrayList<>();
        for (CollectTable item : collectTables){
            AttTableSingle attTableSingle = attTableSingleMapper.selectById(item.getAttId());
            attTableSingles.add(attTableSingle);
        }
        return attTableSingles;
    }
}

