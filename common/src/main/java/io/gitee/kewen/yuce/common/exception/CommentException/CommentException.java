package io.gitee.kewen.yuce.common.exception.CommentException;

import io.gitee.kewen.yuce.common.exception.CommonException;

public class CommentException extends CommonException {

    public static final CommentException Comment_No_Exist = new CommentException("暂时没有评论哦");


    public CommentException(String message) {
        super(message);
    }

    public CommentException(String message, Throwable cause) {
        super(message, cause);
    }

}
