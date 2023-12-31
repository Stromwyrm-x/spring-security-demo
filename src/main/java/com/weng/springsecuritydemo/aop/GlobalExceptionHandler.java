package com.weng.springsecuritydemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler<T>
{
//    /**
//     * 业务异常
//     * @param businessException
//     * @return
//     */
//    @ExceptionHandler(BusinessException.class)
//    public Result<T> businessExceptionHandler(BusinessException businessException)
//    {
//        log.error("BusinessException:",businessException);
//        return Result.error(businessException.getResultCodeEnum(),businessException.getMessage());
//    }
//
//    /**
//     * 参数校验失败的异常处理器
//     */
//    @ExceptionHandler(BindException.class)
//    public Result<T> handleBindException(BindException bindException) {
//        String message = Objects.requireNonNull(bindException.getBindingResult().getFieldError()).getDefaultMessage();
//        log.error("BindException:",bindException);
//        return Result.error(ResultCodeEnum.PARAMS_ERROR,message);
//    }

    /**
     * 参数校验失败的异常处理器
     */
//    @ExceptionHandler(HandlerMethodValidationException.class)
//    public Result<T> handleValidationException(HandlerMethodValidationException handlerMethodValidationException) {
//        log.error("HandlerMethodValidationException:",handlerMethodValidationException);
//        ResponseEntity.ok("fc");
//        return Result.error(ResultCodeEnum.PARAMS_ERROR, handlerMethodValidationException.getMessage());
//    }

    /**
     * 兜底
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception exception)
    {
        log.error("Exception:{}",exception.getClass());
        return "Result.error(ResultCodeEnum.SYSTEM_ERROR);";
    }

}
