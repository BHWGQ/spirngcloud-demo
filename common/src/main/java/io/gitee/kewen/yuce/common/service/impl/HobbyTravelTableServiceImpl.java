package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.HobbyTravelTableMapper;
import io.gitee.kewen.yuce.common.model.entity.CollectTravelTable;
import io.gitee.kewen.yuce.common.model.entity.HobbyTravelTable;
import io.gitee.kewen.yuce.common.service.HobbyTravelTableService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HobbyTravelTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-24 14:44:34
 */
@Service("hobbyTravelTableService")
public class HobbyTravelTableServiceImpl extends ServiceImpl<HobbyTravelTableMapper, HobbyTravelTable> implements HobbyTravelTableService {

    @Resource
    private HobbyTravelTableMapper hobbyTravelTableMapper;

    @Override
    public List<HobbyTravelTable> getByUserId(Long userId) {
        LambdaQueryWrapper<HobbyTravelTable> wrapper = new QueryWrapper<HobbyTravelTable>().lambda()
                .eq(HobbyTravelTable::getUserId,userId);
        List<HobbyTravelTable> hobbyTravelTables = hobbyTravelTableMapper.selectList(wrapper);
        if (CollectionUtil.isEmpty(hobbyTravelTables)){
            throw new RuntimeException("该用户暂无喜欢的游记");
        }
        return hobbyTravelTables;
    }
}

