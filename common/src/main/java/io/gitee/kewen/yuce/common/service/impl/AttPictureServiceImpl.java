package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.AttPictureMapper;
import io.gitee.kewen.yuce.common.model.entity.AttPicture;
import io.gitee.kewen.yuce.common.service.AttPictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (AttPicture)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:01
 */
@Service
public class AttPictureServiceImpl extends ServiceImpl<AttPictureMapper, AttPicture> implements AttPictureService {

    @Resource
    private AttPictureMapper attPictureMapper;

    @Override
    public String getByAttId(Integer id) {
        LambdaQueryWrapper<AttPicture> lambdaQueryWrapper = new QueryWrapper<AttPicture>().lambda()
                .eq(AttPicture::getAttId,id);
        AttPicture attPicture = attPictureMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtil.isNull(attPicture)){
            throw new RuntimeException("未找到该经典图片");
        }
        return attPicture.getAttPicture();
    }
}

