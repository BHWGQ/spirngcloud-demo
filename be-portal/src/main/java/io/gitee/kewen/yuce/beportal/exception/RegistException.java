package io.gitee.kewen.yuce.beportal.exception;

import io.gitee.kewen.yuce.common.exception.CommonException;

public class RegistException extends CommonException {

    public static final RegistException User_Is_Exist = new RegistException("用户已存在");

    public static final RegistException User_Is_Exist_Is_Ban = new RegistException("用户手机号已存在并且被禁用");

    public RegistException(String message) {
        super(message);
    }

    public RegistException(String message, Throwable cause) {
        super(message, cause);
    }

}
