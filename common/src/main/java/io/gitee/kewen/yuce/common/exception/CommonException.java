package io.gitee.kewen.yuce.common.exception;

/**
 * @author
 * @version 1.0
 * @description
 * @since 2023-10-07 23:34:14
 */
public class CommonException extends RuntimeException{

    public CommonException(String message) {
        super(message);
    }

    public CommonException(String message,Throwable throwable){
        super(message,throwable);
    }


}
