package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.gitee.kewen.yuce.common.mapper.CollectTravelTableMapper;
import io.gitee.kewen.yuce.common.model.dto.req.TravelInsertInfoReq;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelResp;
import io.gitee.kewen.yuce.common.model.entity.CollectTable;
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
            return null;
        }
        return collectTravelTables;
    }

    @Override
    public TravelResp insertCollectByReq(TravelInsertInfoReq req) {
        LambdaQueryWrapper<CollectTravelTable> collectTravelTableLambdaQueryWrapper = new QueryWrapper<CollectTravelTable>().lambda()
                .eq(CollectTravelTable::getUserId,req.getUserId())
                .eq(CollectTravelTable::getTravelId,req.getTravelId());
        CollectTravelTable collectTravelTable = collectTravelTableMapper.selectOne(collectTravelTableLambdaQueryWrapper);
        if (ObjectUtil.isNotNull(collectTravelTable)){
            throw new RuntimeException("您已收藏该游记了哦");
        }
        CollectTravelTable collectTravelTable1 = new CollectTravelTable();
        collectTravelTable1.setUserId(req.getUserId());
        collectTravelTable1.setTravelId(req.getTravelId());
        int affet = collectTravelTableMapper.insert(collectTravelTable1);
        if (affet == 0){
            throw new RuntimeException("收藏失败");
        }
        return new TravelResp(req.getUserId());
    }

    @Override
    public TravelResp deleteCollectByReq(TravelInsertInfoReq req) {
        LambdaQueryWrapper<CollectTravelTable> collectTravelTableLambdaQueryWrapper = new QueryWrapper<CollectTravelTable>().lambda()
                .eq(CollectTravelTable::getUserId,req.getUserId())
                .eq(CollectTravelTable::getTravelId,req.getTravelId());
        CollectTravelTable collectTravelTable = collectTravelTableMapper.selectOne(collectTravelTableLambdaQueryWrapper);
        if (ObjectUtil.isNull(collectTravelTable)){
            throw new RuntimeException("您好像并没有收藏该游记哦");
        }
        int affet = collectTravelTableMapper.delete(collectTravelTableLambdaQueryWrapper);
        if (affet == 0){
            throw new RuntimeException("删除失败");
        }
        return new TravelResp(req.getUserId());
    }

    @Override
    public int queryCollectCounts(Integer travelId) {
        LambdaQueryWrapper<CollectTravelTable> wrapper = new QueryWrapper<CollectTravelTable>().lambda()
                .eq(CollectTravelTable::getTravelId,travelId);
        List<CollectTravelTable> collectTravelTables = collectTravelTableMapper.selectList(wrapper);
        int count = collectTravelTables.size();
        return count;
    }

    @Override
    public Boolean querySature(Long userId, Integer travelId) {
        LambdaQueryWrapper<CollectTravelTable> wrapper = new QueryWrapper<CollectTravelTable>().lambda()
                .eq(CollectTravelTable::getUserId,userId)
                .eq(CollectTravelTable::getTravelId,travelId);
        CollectTravelTable collectTravelTable = collectTravelTableMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(collectTravelTable)){
            return false;
        }
        return true;
    }
}

