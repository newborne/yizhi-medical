package com.yizhi.util.common.exception;

import com.yizhi.util.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.fail();
    }

    @ExceptionHandler(YizhiException.class)
    @ResponseBody
    public Result error(YizhiException e) {
        e.printStackTrace();
        return Result.fail();
    }
}
