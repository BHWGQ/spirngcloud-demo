package io.gitee.kewen.yuce.beportal.exception;

import io.gitee.kewen.yuce.common.exception.CommonException;

public class LoginException extends CommonException {
    public static final LoginException Login_ShiXiao = new LoginException("登录凭证已过期，请重新登录");

    public static final LoginException User_Not_Exist = new LoginException("用户不存在");

    public static final LoginException User_Is_Ban = new LoginException("用户已被封禁");

    public static final LoginException User_Password_Error = new LoginException("用户账号密码错误");

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

}
