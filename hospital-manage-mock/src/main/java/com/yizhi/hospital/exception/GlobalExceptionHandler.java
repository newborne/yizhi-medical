package com.yizhi.hospital.exception;

import com.yizhi.hospital.exception.YizhiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The type Global exception handler.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Error string.
     *
     * @param e the e
     * @return the string
     */
    @ExceptionHandler(Exception.class)
    public String error(Exception e) {
        e.printStackTrace();
        return "error";
    }

    /**
     * Error string.
     *
     * @param e     the e
     * @param model the model
     * @return the string
     */
    @ExceptionHandler(YizhiException.class)
    public String error(YizhiException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
