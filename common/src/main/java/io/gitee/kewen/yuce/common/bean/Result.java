package io.gitee.kewen.yuce.common.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: Ding
 * @version: 1.0
 * @createTime: 2023-10-05 23:04:33
 * @modify:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private Integer code;

    private T data;

    private String msg;

    public final static Integer SUCCESS_CODE = 200;

    public final static Integer ERROR_CODE = 500;

    public final static Result<String> NOT_LOGIN = create(401, "您尚未登录，请登录后再访问！");

    public final static Result<String> TOKEN_EXPIRE = create(401, "您的登录已过期，请重新登录！");

    public final static Result<String> TOKEN_INVALID = create(401, "您的认证信息无效，请重新登录！");

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> create(Integer code, String msg) {
        return new Result<>(code, msg);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(SUCCESS_CODE, data, msg);
    }

    public static <T> Result<T> success(T data) {
        return success(data, "成功");
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<T>(ERROR_CODE, null, msg);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(ERROR_CODE, data, "失败");
    }

}
