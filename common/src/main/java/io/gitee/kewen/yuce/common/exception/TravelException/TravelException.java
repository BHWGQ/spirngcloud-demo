package io.gitee.kewen.yuce.common.exception.TravelException;

import io.gitee.kewen.yuce.common.exception.CommentException.CommentException;
import io.gitee.kewen.yuce.common.exception.RestException.RestException;

public class TravelException extends CommentException {

    public static final TravelException Query_No_Exist = new TravelException("没有查到该帖子信息");

    public static final TravelException Delete_Have_Error = new TravelException("删除失败");

    public TravelException(String message) {
        super(message);
    }

}
