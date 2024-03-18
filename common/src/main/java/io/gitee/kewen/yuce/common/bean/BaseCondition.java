package io.gitee.kewen.yuce.common.bean;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @description:
 * @author: Qdw
 * @version: 1.0
 * @createTime: 2023-10-05 23:21:46
 * @modify:
 */

@Data
public class BaseCondition<T> {

    private Integer pageNum;

    private Integer pageSize;

    public Page<T> getPage() {
        return Page.of(pageNum, pageSize);
    }

    public LambdaQueryWrapper<T> getLambdaWrapper() {
        return new LambdaQueryWrapper<>();
    }

    public QueryWrapper<T> getWrapper() {
        return new QueryWrapper<>();
    }
}
