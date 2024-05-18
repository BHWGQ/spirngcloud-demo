package io.gitee.kewen.yuce.beattractions.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.beattractions.dto.req.AttPeopleNumberReq;
import io.gitee.kewen.yuce.beattractions.exception.QueryException;
import io.gitee.kewen.yuce.beattractions.service.AttTableSingleService;
import io.gitee.kewen.yuce.beattractions.mapper.AttTableSingleMapper;
import io.gitee.kewen.yuce.beattractions.dto.resp.AttHomePageQueryTen;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Resource
    private RedisTemplate<String,List<AttHomePageQueryTen>> redisTemplate;

    @Override
    public List<AttHomePageQueryTen> queryTenInfo() {
        List<AttHomePageQueryTen> attHomePageQueryTens = attTableSingleMapper.getRandomRecords();
        if (ObjectUtil.isNull(attHomePageQueryTens)){
            throw QueryException.Data_Not_Exist;
        }
        redisTemplate.opsForValue().set("homePageQuery", attHomePageQueryTens, 1800, TimeUnit.SECONDS);
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

    @Override
    public int forecast(AttPeopleNumberReq req) throws IOException, InterruptedException {
        String pythonScriptPath = "C:\\Users\\wgq\\PycharmProjects\\lanqiao\\Demo\\Test20.py";

        String inputName = req.getAttName();
        String inputDate = String.valueOf(req.getQueryTime());

        ProcessBuilder pb = new ProcessBuilder("python", pythonScriptPath);
        pb.redirectErrorStream(true);
        Process process = pb.start();

        // 发送数据到Python脚本
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(process.getOutputStream(), "gb2312"))) {
            out.write(inputName + "\n");
            out.write(inputDate + "\n");
            out.flush();
        }

        // 读取Python脚本的输出
        StringBuilder resultBuilder = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream(), "gb2312"))) {
            String line;
            while ((line = in.readLine()) != null) {
                resultBuilder.append(line);
            }
        }
        String resultstr = resultBuilder.toString().trim();
        int predictedCount = 0;
        double number = Double.parseDouble(resultstr);
        double number1 = Math.ceil(number);
        int result = (int) Math.round(number1);
        predictedCount = result;
        return predictedCount;
    }


}

