package io.gitee.kewen.yuce.beportal.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.beportal.dto.req.RegistReq;
import io.gitee.kewen.yuce.beportal.dto.req.SysLoginReq;
import io.gitee.kewen.yuce.beportal.dto.req.UpdateUserInfoReq;
import io.gitee.kewen.yuce.beportal.dto.resp.RegistResp;
import io.gitee.kewen.yuce.beportal.dto.resp.SysBaseResp;
import io.gitee.kewen.yuce.beportal.dto.resp.SysLoginResp;
import io.gitee.kewen.yuce.beportal.dto.resp.UpdateUserInfoResp;
import io.gitee.kewen.yuce.beportal.exception.LoginException;
import io.gitee.kewen.yuce.beportal.exception.RegistException;
import io.gitee.kewen.yuce.beportal.service.SysLoginService;
import io.gitee.kewen.yuce.common.jwt.JwtUtil;
import io.gitee.kewen.yuce.common.mapper.SysLoginMapper;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class SysLoginServiceImpl extends ServiceImpl<SysLoginMapper, LoginTable> implements SysLoginService {
    //需要用到jwt，redis
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private SysLoginMapper sysLoginMapper;

    private static final long EXPIRE_TIME = 30 * 60 * 1000;

    @Override
    public SysLoginResp login(SysLoginReq req) {
        //判断用户是否存在
        LambdaQueryWrapper<LoginTable> lambdaQueryWrapper = new QueryWrapper<LoginTable>().lambda()
                .eq(LoginTable::getUserId,req.getUserId());
        LoginTable loginTable = sysLoginMapper.selectOne(lambdaQueryWrapper);
        //如果存在，状态是否被封禁
        if (ObjectUtil.isNull(loginTable)){
            throw LoginException.User_Not_Exist;
        }
        if (!BCrypt.checkpw(req.getPassword(),loginTable.getPassword())){
            throw LoginException.User_Password_Error;
        }
        if (loginTable.getStatus() != 0){
            throw LoginException.User_Is_Ban;
        }
        //生成token
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userId", req.getUserId());
            String token = JwtUtil.sign(claims);
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            stringRedisTemplate.opsForValue().set("login:" + token, loginTable.getUserName(), 1800, TimeUnit.SECONDS);
            SysBaseResp sysBaseResp = new SysBaseResp(loginTable.getUserId(),loginTable.getUserName(),loginTable.getPhoneNumber(),loginTable.getCreateTime());
            SysLoginResp sysLoginResp = new SysLoginResp();
            sysLoginResp.setSysBaseResp(sysBaseResp);
            sysLoginResp.setToken(token);
            sysLoginResp.setExpireTime(date);
            sysLoginResp.setSignature(loginTable.getSignature());
            sysLoginResp.setPicture(loginTable.getPicture());
            return sysLoginResp;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RegistResp regist(RegistReq req) {
        LambdaQueryWrapper<LoginTable> registQueryWrapper = new QueryWrapper<LoginTable>().lambda()
                .eq(LoginTable::getUserId,req.getUserId())
                .or()
                .eq(LoginTable::getPhoneNumber,req.getPhoneNumber());
        LoginTable table = sysLoginMapper.selectOne(registQueryWrapper);
        if (ObjectUtil.isNotNull(table)){
            throw RegistException.User_Is_Exist;
        }
        LambdaQueryWrapper<LoginTable> lambdaQueryWrapper = new QueryWrapper<LoginTable>().lambda()
                .eq(LoginTable::getPhoneNumber,req.getPhoneNumber())
                .eq(LoginTable::getStatus,1);
        LoginTable regist = sysLoginMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtil.isNotNull(regist)){
            throw RegistException.User_Is_Exist_Is_Ban;
        }
        req.setPassword(BCrypt.hashpw(req.getPassword()));
        if (Objects.isNull(req.getRoler())){
            req.setRoler("user");
            insertUser(req);
        }else {
            insertUser(req);
        }
        return new RegistResp(req.getUserName());
    }

    @Override
    public LoginTable getUserById(Long userId) {
        LambdaQueryWrapper<LoginTable> lambdaQueryWrapper = new QueryWrapper<LoginTable>().lambda()
                .eq(LoginTable::getUserId,userId);
        LoginTable loginTable = sysLoginMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtil.isNull(loginTable)){
            throw new RuntimeException("数据库发生了错误");
        }
        return loginTable;
    }

    @Override
    public UpdateUserInfoResp updateUserInfo(UpdateUserInfoReq req) {
        LambdaQueryWrapper<LoginTable> wrapper = new QueryWrapper<LoginTable>().lambda()
                .eq(LoginTable::getUserId,req.getUserId());
        LoginTable loginTable = sysLoginMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(loginTable)){
            throw LoginException.User_Not_Exist;
        }
        LambdaUpdateWrapper<LoginTable> lambdaUpdateWrapper = new UpdateWrapper<LoginTable>().lambda()
                .eq(LoginTable::getUserId,req.getUserId())
                .set(LoginTable::getUserName,req.getUserName())
                .set(LoginTable::getSignature,req.getSignature());
        int affect = sysLoginMapper.update(null,lambdaUpdateWrapper);
        if (affect == 0){
            throw new RuntimeException("更新失败");
        }
        return new UpdateUserInfoResp(req.getUserName());
    }

    private void insertUser(RegistReq req) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        String picture = "http://s9tnzuteo.hd-bkt.clouddn.com/travel-plan-person-picture/base.png";
        String signature = "这家伙很懒，啥都不想说";
        LoginTable rolerNull = new LoginTable(req.getUserId(),req.getUserName(),req.getPassword(),req.getPhoneNumber(),req.getRoler(),0,currentDateTime,signature,picture);
        int rowsAffected = sysLoginMapper.insert(rolerNull);
        if (rowsAffected <= 0) {
            throw new RuntimeException("插入失败，可能是由于主键冲突或其他原因。");
        }
    }
}
