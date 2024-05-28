package io.gitee.kewen.yuce.common.exception.PortalException;

import io.gitee.kewen.yuce.common.exception.CommonException;

public class PortalException extends CommonException {

    public static final PortalException Subscribe_Exist = new PortalException("您已经关注了该用户");

    public static final PortalException Subscribe_Not_Exist = new PortalException("您并没有关注该用户");


    public PortalException(String message) {
        super(message);
    }

    public PortalException(String message, Throwable cause) {
        super(message, cause);
    }

}
