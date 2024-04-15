package io.gitee.kewen.yuce.travel.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.consts.RabbitMqConsts;
import io.gitee.kewen.yuce.common.exception.TravelException.TravelException;
import io.gitee.kewen.yuce.common.mapper.TripTableMapper;
import io.gitee.kewen.yuce.common.model.dto.req.TripInsertReq;
import io.gitee.kewen.yuce.common.model.entity.TripTable;
import io.gitee.kewen.yuce.travel.service.TripTableService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * (TripTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("tripTableService")
public class TripTableServiceImpl extends ServiceImpl<TripTableMapper, TripTable> implements TripTableService {
    @Resource
    private TripTableMapper tripTableMapper;

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public Boolean insertTrip(TripInsertReq req) {
        LambdaQueryWrapper<TripTable> wrapper = new QueryWrapper<TripTable>().lambda()
                .eq(TripTable::getUserId,req.getUserId())
                .eq(TripTable::getOriginAdd,req.getOriginAdd())
                .eq(TripTable::getDesAdd,req.getDesAdd());
        TripTable tripTable = tripTableMapper.selectOne(wrapper);
        if (ObjectUtil.isNotNull(tripTable)){
            throw TravelException.exist;
        }
        if (req.getCreateTime().isBefore(LocalDateTime.now())){
            throw TravelException.Time_Error;
        }
        TripTable tripTableObject = TripTable.builder()
                .userId(req.getUserId())
                .content(req.getContent())
                .createTime(req.getCreateTime())
                .leaveTime(req.getLeaveTime())
                .originAdd(req.getOriginAdd())
                .desAdd(req.getDesAdd())
                .build();
        tripTableMapper.insert(tripTableObject);
        Duration duration = Duration.between(LocalDateTime.now(),req.getCreateTime());
        Integer milliseconds = Math.toIntExact(duration.toMillis());
        rabbitTemplate.convertAndSend(RabbitMqConsts.DELAYED_EXCHANGE_NAME,RabbitMqConsts.DELAYED_ROUTING_KEY,req,correlationData -> {
            correlationData.getMessageProperties().setDelay(milliseconds);
            return correlationData;
        });
        return true;
    }
}

