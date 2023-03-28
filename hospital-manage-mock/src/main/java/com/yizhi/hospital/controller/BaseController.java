package com.yizhi.hospital.controller;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * The type Base controller.
 */
public class BaseController {

    /**
     * The constant MESSAGE_SUCCESS.
     */
//提示信息
    public final static String MESSAGE_SUCCESS = "操作成功！";
    /**
     * The constant MESSAGE_FAILURE.
     */
    public final static String MESSAGE_FAILURE = "操作失败！";

    /**
     * Success message.
     *
     * @param message            the message
     * @param redirectAttributes the redirect attributes
     */
    protected void successMessage(String message, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", StringUtils.isEmpty(message) ? MESSAGE_SUCCESS : message);
        redirectAttributes.addFlashAttribute("messageType", 1);
    }

    /**
     * Failure message.
     *
     * @param message            the message
     * @param redirectAttributes the redirect attributes
     */
    protected void failureMessage(String message, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", StringUtils.isEmpty(message) ? MESSAGE_FAILURE : message);
        redirectAttributes.addFlashAttribute("messageType", 0);
    }

    /**
     * Failure message.
     *
     * @param message the message
     * @param request the request
     */
    protected void failureMessage(String message, HttpServletRequest request) {
        request.setAttribute("message", StringUtils.isEmpty(message) ? MESSAGE_SUCCESS : message);
        request.setAttribute("messageType", 0);
    }

    /**
     * Success page string.
     *
     * @param message the message
     * @param request the request
     * @return the string
     */
    protected String successPage(String message, HttpServletRequest request) {
        request.setAttribute("messagePage", StringUtils.isEmpty(message) ? MESSAGE_SUCCESS : message);
        return "common/successPage";
    }

    /**
     * Failure page string.
     *
     * @param message the message
     * @param request the request
     * @return the string
     */
    protected String failurePage(String message, HttpServletRequest request) {
        request.setAttribute("messagePage", StringUtils.isEmpty(message) ? MESSAGE_FAILURE : message);
        return "common/failurePage";
    }

    /**
     * Gets filters.
     *
     * @param request the request
     * @return the filters
     */
    protected Map<String, Object> getFilters(HttpServletRequest request) {
        Map<String, Object> filters = WebUtils.getParametersStartingWith(request, "s_");
        return filters;
    }
}
