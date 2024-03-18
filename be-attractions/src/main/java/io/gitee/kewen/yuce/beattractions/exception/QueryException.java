package io.gitee.kewen.yuce.beattractions.exception;

import io.gitee.kewen.yuce.common.exception.CommonException;

public class QueryException extends CommonException {
    public static final QueryException Data_Not_Exist = new QueryException("数据未找到");

    public static final QueryException Att_Not_Exist = new QueryException("未找到该景点信息");

    public static final QueryException Att_Picture_Not_Exist = new QueryException("未找到该景点图片");

    public QueryException(String message) {
        super(message);
    }

    public QueryException(String message, Throwable cause) {
        super(message, cause);
    }

}
