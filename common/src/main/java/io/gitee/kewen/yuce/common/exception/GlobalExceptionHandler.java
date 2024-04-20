package io.gitee.kewen.yuce.common.exception;

import io.gitee.kewen.yuce.common.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author wspstart
 * @version 1.0
 * @description
 * @since 2023-10-08 08:21:09
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public Result<String> commonException(CommonException commonException) {
        log.error(commonException.getMessage());
        return Result.fail(commonException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> methodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        int errorCount = bindingResult.getErrorCount();
        // 返回首个错误（当有多个错误时，第一个错误是无法预知的，或许和字段顺序有关）
        ObjectError objectError = bindingResult.getAllErrors()
                .get(0);
        if (log.isDebugEnabled()) {
            log.debug("参数校验失败。共 {} 个错误，返回的第 0 个错误为：\n {} ", errorCount, objectError.toString());
        }
        return Result.fail(objectError.getDefaultMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public Result<String> handleConstraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : constraintViolations) {
            return Result.fail(violation.getMessage());
        }
        return Result.fail("输入验证失败");
    }

    @ExceptionHandler(Exception.class)
    public Result<String> exception(Exception e) {
        log.error(e.getMessage());
        return Result.fail("服务器异常!");
    }

}
