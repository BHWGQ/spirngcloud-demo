package io.gitee.kewen.yuce.common.exception.RestException;

import io.gitee.kewen.yuce.common.exception.CommonException;

public class RestException extends CommonException {

    public static final RestException Add_No_Rest = new RestException("暂时没有餐厅哦");

    public static final RestException Query_No_Rest = new RestException("未查到餐厅信息");

    public static final RestException Query_Error = new RestException("出错啦");

    public static final RestException Add_No_Rest_Picture = new RestException("暂时没有该餐厅的图片哦");

    public RestException(String message) {
        super(message);
    }

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

}
