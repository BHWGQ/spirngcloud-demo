package io.gitee.kewen.yuce.beattractions.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.beattractions.exception.QueryException;
import io.gitee.kewen.yuce.beattractions.service.AttTableSingleService;
import io.gitee.kewen.yuce.beattractions.mapper.AttTableSingleMapper;
import io.gitee.kewen.yuce.beattractions.dto.resp.AttHomePageQueryTen;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AttTableSingle)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("attTableSingleService")
public class AttTableSingleServiceImpl extends ServiceImpl<AttTableSingleMapper, AttTableSingle> implements AttTableSingleService {
    @Resource
    private AttTableSingleMapper attTableSingleMapper;
    @Override
    public List<AttHomePageQueryTen> queryTenInfo() {
        List<AttHomePageQueryTen> attHomePageQueryTens = attTableSingleMapper.getRandomRecords();
        if (ObjectUtil.isNull(attHomePageQueryTens)){
            throw QueryException.Data_Not_Exist;
        }
        return attHomePageQueryTens;
    }

    @Override
    public List<AttTableSingle> getByAttName(String attName) {
        LambdaQueryWrapper<AttTableSingle> attTableSingleLambdaQueryWrapper = new QueryWrapper<AttTableSingle>().lambda()
                .like(AttTableSingle::getAttName,attName);
        List<AttTableSingle> attTableSingle = attTableSingleMapper.selectList(attTableSingleLambdaQueryWrapper);
        if (CollectionUtil.isEmpty(attTableSingle)){
            throw QueryException.Att_Not_Exist;
        }
        return attTableSingle;
    }
}

