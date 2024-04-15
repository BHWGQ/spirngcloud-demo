package io.gitee.kewen.yuce.common.exception.TravelException;

import io.gitee.kewen.yuce.common.exception.CommentException.CommentException;
import io.gitee.kewen.yuce.common.exception.RestException.RestException;

public class TravelException extends CommentException {

    public static final TravelException Query_No_Exist = new TravelException("没有查到该帖子信息");

    public static final TravelException Delete_Have_Error = new TravelException("删除失败");

    public static final TravelException exist = new TravelException("您已经预定了行程");

    public static final TravelException Time_Error = new TravelException("您的提醒时间小于当前时间！");

    public TravelException(String message) {
        super(message);
    }

}
