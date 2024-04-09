package io.gitee.kewen.yuce.beattractions.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.beattractions.dto.req.AttCollectHobbyInsertReq;
import io.gitee.kewen.yuce.beattractions.dto.resp.AttCollectHobbyResp;
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
            throw new RuntimeException("该用户暂时没有喜欢的任何景点信息");
        }
        List<AttTableSingle> attTableSingles = new ArrayList<>();
        for (HobbyTable item : hobbyTables){
            AttTableSingle attTableSingle = attTableSingleMapper.selectById(item.getAttId());
            attTableSingles.add(attTableSingle);
        }
        return attTableSingles;
    }

    @Override
    public AttCollectHobbyResp insertByReq(AttCollectHobbyInsertReq req) {
        LambdaQueryWrapper<HobbyTable> wrapper = new QueryWrapper<HobbyTable>().lambda()
                .eq(HobbyTable::getUserId,req.getUserId())
                .eq(HobbyTable::getAttId,req.getAttId());
        HobbyTable hobbyTable = hobbyTableMapper.selectOne(wrapper);
        if (!ObjectUtil.isNull(hobbyTable)){
            throw new RuntimeException("您已点赞该景点");
        }
        HobbyTable hobbyTable1 = new HobbyTable();
        hobbyTable1.setUserId(req.getUserId());
        hobbyTable1.setAttId(req.getAttId());
        int affets = hobbyTableMapper.insert(hobbyTable1);
        if (affets == 0){
            throw new RuntimeException("收藏失败");
        }
        return new AttCollectHobbyResp(req.getUserId());
    }

    @Override
    public AttCollectHobbyResp DeleteByReq(AttCollectHobbyInsertReq req) {
        LambdaQueryWrapper<HobbyTable> wrapper = new QueryWrapper<HobbyTable>().lambda()
                .eq(HobbyTable::getUserId,req.getUserId())
                .eq(HobbyTable::getAttId,req.getAttId());
        HobbyTable hobbyTable = hobbyTableMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(hobbyTable)){
            throw new RuntimeException("未查到需要删除的喜欢景点信息");
        }
        int affets = hobbyTableMapper.delete(wrapper);
        if (affets == 0){
            throw new RuntimeException("删除失败");
        }
        return new AttCollectHobbyResp(req.getUserId());
    }

    @Override
    public int queryCollectCounts(Integer attId) {
        LambdaQueryWrapper<HobbyTable> wrapper = new QueryWrapper<HobbyTable>().lambda()
                .eq(HobbyTable::getAttId,attId);
        List<HobbyTable> hobbyTables = hobbyTableMapper.selectList(wrapper);
        int count = hobbyTables.size();
        return count;
    }

    @Override
    public Boolean querySature(Long userId, Integer attId) {
        LambdaQueryWrapper<HobbyTable> hobbyTableLambdaQueryWrapper = new QueryWrapper<HobbyTable>().lambda()
                .eq(HobbyTable::getAttId,attId)
                .eq(HobbyTable::getUserId,userId);
        HobbyTable hobbyTable = hobbyTableMapper.selectOne(hobbyTableLambdaQueryWrapper);
        if (ObjectUtil.isNull(hobbyTable)){
            return false;
        }
        return true;
    }
}

