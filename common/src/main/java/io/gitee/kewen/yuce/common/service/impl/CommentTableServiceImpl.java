package io.gitee.kewen.yuce.common.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.gitee.kewen.yuce.common.mapper.CommentTableMapper;
import io.gitee.kewen.yuce.common.mapper.SysLoginMapper;
import io.gitee.kewen.yuce.common.model.dto.req.CommentInsertReq;
import io.gitee.kewen.yuce.common.model.dto.resp.Att_user;
import io.gitee.kewen.yuce.common.model.dto.resp.CommentInsertResp;
import io.gitee.kewen.yuce.common.model.entity.AttTableSingle;
import io.gitee.kewen.yuce.common.model.entity.CommentTable;
import io.gitee.kewen.yuce.common.model.entity.LoginTable;
import io.gitee.kewen.yuce.common.service.AttPictureService;
import io.gitee.kewen.yuce.common.service.AttTableService;
import io.gitee.kewen.yuce.common.service.CommentTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * (CommentTable)表服务实现类
 *
 * @author makejava
 * @since 2024-03-05 12:08:02
 */
@Service("commentTableService")
public class CommentTableServiceImpl extends ServiceImpl<CommentTableMapper, CommentTable> implements CommentTableService {
    @Resource
    private SysLoginMapper loginMapper;

    @Resource
    private CommentTableMapper commentTableMapper;

    @Resource
    private AttPictureService attPictureService;

    @Resource
    private AttTableService attTableService;

    @Transactional
    @Override
    public CommentInsertResp insert(CommentInsertReq req) {
        LambdaQueryWrapper<LoginTable> lambdaQueryWrapper = new QueryWrapper<LoginTable>().lambda()
                .eq(LoginTable::getUserId,req.getUserId())
                .eq(LoginTable::getStatus,0);
        LoginTable loginTable = loginMapper.selectOne(lambdaQueryWrapper);
        if (ObjectUtil.isNull(loginTable)){
            throw new RuntimeException("您已被暂时封禁，无法发言");
        }
        LocalDateTime currentTime = LocalDateTime.now();
        CommentTable commentTable = new CommentTable();
        commentTable.setAttId(req.getAttId());
        commentTable.setUserName(req.getUserName());
        commentTable.setUserId(req.getUserId());
        commentTable.setContent(req.getContent());
        commentTable.setReply(req.getReply());
        commentTable.setCreateTime(currentTime);
        int affectRow = commentTableMapper.insert(commentTable);
        if (affectRow == 0){
            throw new RuntimeException("评论失败，正在抢修!");
        }
        return new CommentInsertResp(req.getUserName());
    }

    @Override
    public List<Att_user> queryByUserId(Long userId) {
        List<CommentTable> commentTables = commentTableMapper.selectAttByUserId(userId);
        if (CollectionUtil.isEmpty(commentTables)){
            return null;
        }
        List<Att_user> att_users = new ArrayList<>();
        for (CommentTable item : commentTables){
            Integer attId = item.getAttId();
            AttTableSingle attTableSingle = attTableService.getById(attId);
            String picture = attPictureService.getByAttId(attId);
            Att_user att_user = new Att_user(picture,attId,attTableSingle.getAttName(),attTableSingle.getAttAddress(),attTableSingle.getScore());
            att_users.add(att_user);
        }
        return att_users;
    }
}

