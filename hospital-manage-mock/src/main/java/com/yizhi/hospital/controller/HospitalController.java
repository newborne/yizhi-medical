package com.yizhi.hospital.controller;

import com.yizhi.hospital.service.ApiService;
import com.yizhi.hospital.service.HospitalService;
import com.yizhi.hospital.helper.HttpRequestHelper;
import com.yizhi.hospital.model.Result;
import com.yizhi.hospital.enums.ResultCodeEnum;
import com.yizhi.hospital.exception.YizhiException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * The type Hospital controller.
 */
@Api(tags = "医院管理接口")
@RestController
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private ApiService apiService;

    /**
     * Agree account lend project result.
     *
     * @param request  the request
     * @param response the response
     * @return the result
     */
    @PostMapping("/order/submitOrder")
    public Result AgreeAccountLendProject(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
//			if(!HttpRequestHelper.isSignEquals(paramMap, apiService.getSignKey())) {
//				throw new YizhiException(ResultCodeEnum.SIGN_ERROR);
//			}
            Map<String, Object> resultMap = hospitalService.submitOrder(paramMap);
            return Result.ok(resultMap);
        } catch (YizhiException e) {
            return Result.fail().message(e.getMessage());
        }
    }

    /**
     * Update pay status result.
     *
     * @param request  the request
     * @param response the response
     * @return the result
     */
    @PostMapping("/order/updatePayStatus")
    public Result updatePayStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
            if (!HttpRequestHelper.isSignEquals(paramMap, apiService.getSignKey())) {
                throw new YizhiException(ResultCodeEnum.SIGN_ERROR);
            }
            hospitalService.updatePayStatus(paramMap);
            return Result.ok();
        } catch (YizhiException e) {
            return Result.fail().message(e.getMessage());
        }
    }

    /**
     * Update cancel status result.
     *
     * @param request  the request
     * @param response the response
     * @return the result
     */
    @PostMapping("/order/updateCancelStatus")
    public Result updateCancelStatus(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Object> paramMap = HttpRequestHelper.switchMap(request.getParameterMap());
//			if(!HttpRequestHelper.isSignEquals(paramMap, apiService.getSignKey())) {
//				throw new YizhiException(ResultCodeEnum.SIGN_ERROR);
//			}
            hospitalService.updateCancelStatus(paramMap);
            return Result.ok();
        } catch (YizhiException e) {
            return Result.fail().message(e.getMessage());
        }
    }
}

