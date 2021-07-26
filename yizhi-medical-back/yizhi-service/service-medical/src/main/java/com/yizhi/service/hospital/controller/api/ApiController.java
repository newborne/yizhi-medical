package com.yizhi.service.hospital.controller.api;

import com.yizhi.common.util.exception.YizhiException;

import com.yizhi.common.util.result.Result;
import com.yizhi.common.util.result.ResultCodeEnum;

import com.yizhi.models.model.hospital.Department;
import com.yizhi.models.model.hospital.Hospital;
import com.yizhi.models.model.hospital.Schedule;
import com.yizhi.models.vo.hospital.ScheduleQueryVo;
import com.yizhi.service.hospital.service.DepartmentService;
import com.yizhi.service.hospital.service.HospitalService;
import com.yizhi.service.hospital.service.HospitalSettingService;
import com.yizhi.service.hospital.service.ScheduleService;
import com.yizhi.service.util.helper.HttpRequestHelper;
import com.yizhi.service.util.utils.MD5;
import com.yizhi.models.vo.hospital.DepartmentQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "医院Api")
@RestController
@RequestMapping("/api/medical")
//@CrossOrigin
public class ApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private HospitalSettingService hospitalSettingService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    //删除排班
    @ApiOperation(value = "删-排班")
    @PostMapping("schedule/remove")
    public Result remove(HttpServletRequest request) {
        //获取传递过来科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号和排班编号
        String hospitalCode = (String)paramMap.get("hospitalCode");
        String hospitalScheduleId = (String)paramMap.get("hospitalScheduleId");

        //TODO 签名校验

        scheduleService.delete(hospitalCode,hospitalScheduleId);
        return Result.ok();
    }

    //查询排班接口
    @ApiOperation(value = "查-排班")
    @PostMapping("schedule/list")
    public Result findSchedule(HttpServletRequest request) {
        //获取传递过来科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //医院编号
        String hospitalCode = (String)paramMap.get("hospitalCode");

        //科室编号
        String departCode = (String)paramMap.get("departCode");
        //当前页 和 每页记录数
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : Integer.parseInt((String)paramMap.get("limit"));
        //TODO 签名校验

        ScheduleQueryVo scheduleQueryVo = new ScheduleQueryVo();
        scheduleQueryVo.setHospitalCode(hospitalCode);
        scheduleQueryVo.setDepartCode(departCode);
        //调用service方法
        Page<Schedule> pageModel = scheduleService.findAll(page,limit,scheduleQueryVo);
        return Result.ok(pageModel);
    }

    //上传排班接口
    @ApiOperation(value = "增-排班")
    @PostMapping("saveSchedule")
    public Result saveSchedule(HttpServletRequest request) {
        //获取传递过来科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //TODO 签名校验
        scheduleService.save(paramMap);
        return Result.ok();
    }

    //删除科室接口
    @ApiOperation(value = "删-科室")
    @PostMapping("department/remove")
    public Result removeDepartment(HttpServletRequest request) {
        //获取传递过来科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //医院编号 和 科室编号
        String hospitalCode = (String)paramMap.get("hospitalCode");
        String departCode = (String)paramMap.get("departCode");
        //TODO 签名校验
        departmentService.delete(hospitalCode,departCode);
        return Result.ok();
    }

    //查询科室接口
    @ApiOperation(value = "查-科室")
    @PostMapping("department/list")
    public Result findDepartment(HttpServletRequest request) {
        //获取传递过来科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //医院编号
        String hospitalCode = (String)paramMap.get("hospitalCode");
        //当前页 和 每页记录数
        int page = StringUtils.isEmpty(paramMap.get("page")) ? 1 : Integer.parseInt((String)paramMap.get("page"));
        int limit = StringUtils.isEmpty(paramMap.get("limit")) ? 1 : Integer.parseInt((String)paramMap.get("limit"));
        //TODO 签名校验

        DepartmentQueryVo departmentQueryVo = new DepartmentQueryVo();
        departmentQueryVo.setHospitalCode(hospitalCode);
        //调用service方法
        Page<Department> pageModel = departmentService.findAll(page,limit,departmentQueryVo);
        return Result.ok(pageModel);
    }

    //上传科室接口
    @ApiOperation(value = "增-科室")
    @PostMapping("saveDepartment")
    public Result saveDepartment(HttpServletRequest request) {
        //获取传递过来科室信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //获取医院编号
        String hospitalCode = (String)paramMap.get("hospitalCode");
        //1 获取医院系统传递过来的签名,签名进行MD5加密
        String hospitalSign = (String)paramMap.get("sign");

        //2 根据传递过来医院编码，查询数据库，查询签名
        String signKey = hospitalSettingService.getSignKeyByHospitalCode(hospitalCode);

        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //4 判断签名是否一致
        if(!hospitalSign.equals(signKeyMd5)) {
            throw new YizhiException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service的方法
        departmentService.save(paramMap);
        return Result.ok();
    }

    //查询医院
    @ApiOperation(value = "查-医院")
    @PostMapping("hospital/show")
    public Result getHospital(HttpServletRequest request) {
        //获取传递过来医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);
        //获取医院编号
        String hospitalCode = (String)paramMap.get("hospitalCode");
        //1 获取医院系统传递过来的签名,签名进行MD5加密
        String hospitalSign = (String)paramMap.get("sign");

        //2 根据传递过来医院编码，查询数据库，查询签名
        String signKey = hospitalSettingService.getSignKeyByHospitalCode(hospitalCode);

        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //4 判断签名是否一致
        if(!hospitalSign.equals(signKeyMd5)) {
            throw new YizhiException(ResultCodeEnum.SIGN_ERROR);
        }

        //调用service方法实现根据医院编号查询
        Hospital hospital = hospitalService.findByHospitalCode(hospitalCode);
        return Result.ok(hospital);
    }

    //上传医院接口
    @ApiOperation(value = "增-医院")
    @PostMapping("saveHospital")
    public Result saveHosp(HttpServletRequest request) {
        //获取传递过来医院信息
        Map<String, String[]> requestMap = request.getParameterMap();
        Map<String, Object> paramMap = HttpRequestHelper.switchMap(requestMap);

        //1 获取医院系统传递过来的签名,签名进行MD5加密
        String hospitalSign = (String)paramMap.get("sign");

        //2 根据传递过来医院编码，查询数据库，查询签名
        String hospitalCode = (String)paramMap.get("hospitalCode");
        String signKey = hospitalSettingService.getSignKeyByHospitalCode(hospitalCode);

        //3 把数据库查询签名进行MD5加密
        String signKeyMd5 = MD5.encrypt(signKey);

        //4 判断签名是否一致
        if(!hospitalSign.equals(signKeyMd5)) {
            throw new YizhiException(ResultCodeEnum.SIGN_ERROR);
        }

        //传输过程中“+”转换为了“ ”，因此我们要转换回来
        String logoData = (String)paramMap.get("logoData");
        logoData = logoData.replaceAll(" ","+");
        paramMap.put("logoData",logoData);

        //调用service的方法
        hospitalService.save(paramMap);
        return Result.ok();
    }

}
