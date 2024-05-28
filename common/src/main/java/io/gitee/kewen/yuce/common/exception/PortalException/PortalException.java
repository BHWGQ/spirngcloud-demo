package io.gitee.kewen.yuce.common.exception.PortalException;

import io.gitee.kewen.yuce.common.exception.CommonException;

public class PortalException extends CommonException {

    public static final PortalException Comment_No_Exist = new PortalException("暂时没有关注哦");


    public PortalException(String message) {
        super(message);
    }

    public PortalException(String message, Throwable cause) {
        super(message, cause);
    }

}
