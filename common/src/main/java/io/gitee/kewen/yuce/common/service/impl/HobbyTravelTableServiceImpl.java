package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.HobbyTravelTableMapper;
import io.gitee.kewen.yuce.common.model.dto.req.TravelInsertInfoReq;
import io.gitee.kewen.yuce.common.model.dto.resp.TravelResp;
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

    @Override
    public TravelResp insertHobby(TravelInsertInfoReq req) {
        LambdaQueryWrapper<HobbyTravelTable> collectTravelTableLambdaQueryWrapper = new QueryWrapper<HobbyTravelTable>().lambda()
                .eq(HobbyTravelTable::getUserId,req.getUserId())
                .eq(HobbyTravelTable::getTravelId,req.getTravelId());
        HobbyTravelTable hobbyTravelTable = hobbyTravelTableMapper.selectOne(collectTravelTableLambdaQueryWrapper);
        if (ObjectUtil.isNotNull(hobbyTravelTable)){
            throw new RuntimeException("不要重复点赞哦");
        }
        HobbyTravelTable hobbyTravelTable1 = new HobbyTravelTable();
        hobbyTravelTable1.setUserId(req.getUserId());
        hobbyTravelTable1.setTravelId(req.getTravelId());
        int affet = hobbyTravelTableMapper.insert(hobbyTravelTable1);
        if (affet == 0){
            throw new RuntimeException("点赞失败");
        }
        return new TravelResp(req.getUserId());
    }

    @Override
    public TravelResp deleteHobby(TravelInsertInfoReq req) {
        LambdaQueryWrapper<HobbyTravelTable> collectTravelTableLambdaQueryWrapper = new QueryWrapper<HobbyTravelTable>().lambda()
                .eq(HobbyTravelTable::getUserId,req.getUserId())
                .eq(HobbyTravelTable::getTravelId,req.getTravelId());
        HobbyTravelTable hobbyTravelTable = hobbyTravelTableMapper.selectOne(collectTravelTableLambdaQueryWrapper);
        if (ObjectUtil.isNull(hobbyTravelTable)){
            throw new RuntimeException("您暂时好像并未点赞哦");
        }
        int affet = hobbyTravelTableMapper.delete(collectTravelTableLambdaQueryWrapper);
        if (affet == 0){
            throw new RuntimeException("删除失败");
        }
        return new TravelResp(req.getUserId());
    }

    @Override
    public int queryHobbyCounts(Integer travelId) {
        LambdaQueryWrapper<HobbyTravelTable> wrapper = new QueryWrapper<HobbyTravelTable>().lambda()
                .eq(HobbyTravelTable::getTravelId,travelId);
        List<HobbyTravelTable> hobbyTravelTables = hobbyTravelTableMapper.selectList(wrapper);
        int count = hobbyTravelTables.size();
        return count;
    }

    @Override
    public Boolean querySature(Long userId, Integer travelId) {
        LambdaQueryWrapper<HobbyTravelTable> hobbyTravelTableLambdaQueryWrapper = new QueryWrapper<HobbyTravelTable>().lambda()
                .eq(HobbyTravelTable::getUserId,userId)
                .eq(HobbyTravelTable::getTravelId,travelId);
        HobbyTravelTable hobbyTravelTable = hobbyTravelTableMapper.selectOne(hobbyTravelTableLambdaQueryWrapper);
        if (ObjectUtil.isNull(hobbyTravelTable)){
            return false;
        }
        return true;
    }
}

