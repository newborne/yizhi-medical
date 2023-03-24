package com.yizhi.service.medical.api;

import com.yizhi.common.util.result.Result;
import com.yizhi.models.model.medical.Hospital;
import com.yizhi.models.model.medical.Schedule;
import com.yizhi.models.vo.medical.DepartmentVo;
import com.yizhi.models.vo.medical.HospitalQueryVo;
import com.yizhi.models.vo.medical.ScheduleOrderVo;
import com.yizhi.models.vo.order.SignInfoVo;
import com.yizhi.service.medical.service.DepartmentService;
import com.yizhi.service.medical.service.HospitalService;
import com.yizhi.service.medical.service.HospitalSettingService;
import com.yizhi.service.medical.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Api(tags = "用户端WebApi")
@RestController
@RequestMapping("/api/medical/hospital")
public class UserWebApiController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private HospitalSettingService hospitalSettingService;

    @ApiOperation(value = "查询医院列表")
    @GetMapping("findHospitalPage/{page}/{limit}")
    public Result findHospitalPage(@PathVariable Integer page,
                               @PathVariable Integer limit,
                               HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> hospitals = hospitalService.findPage(page, limit, hospitalQueryVo);
        return Result.ok(hospitals);
    }

    @ApiOperation(value = "查-医院-by-hospitalName")
    @GetMapping("findHospitalList/{hospitalName}")
    public Result findHospitalList(@PathVariable String hospitalName) {
        List<Hospital> list = hospitalService.findListByHospitalNameLike(hospitalName);
        return Result.ok(list);
    }

    @ApiOperation(value = "根据医院编号获取科室")
    @GetMapping("findDepartmentTree/{hospitalCode}")
    public Result findDepartmentTree(@PathVariable String hospitalCode) {
        List<DepartmentVo> list = departmentService.findTree(hospitalCode);
        return Result.ok(list);
    }

    @ApiOperation(value = "根据医院编号获取医院预约挂号详情")
    @GetMapping("hospitalItem/{hospitalCode}")
    public Result findHospitalItemByHospitalCode(@PathVariable String hospitalCode) {
        Map<String, Object> map = hospitalService.findItemByHospitalCode(hospitalCode);
        return Result.ok(map);
    }

    @ApiOperation(value = "获取可预约排班数据")
    @GetMapping("auth/findBookingRule/{page}/{limit}/{hospitalCode}/{departCode}")
    public Result findBookingRule(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Integer page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Integer limit,
            @ApiParam(name = "hospitalCode", value = "医院code", required = true)
            @PathVariable String hospitalCode,
            @ApiParam(name = "departCode", value = "科室code", required = true)
            @PathVariable String departCode) {
        return Result.ok(scheduleService.findBookingRule(page, limit, hospitalCode, departCode));
    }

    @ApiOperation(value = "获取排班数据")
    @GetMapping("auth/findScheduleList/{hospitalCode}/{departCode}/{workDate}")
    public Result findScheduleList(
            @ApiParam(name = "hospitalCode", value = "医院code", required = true)
            @PathVariable String hospitalCode,
            @ApiParam(name = "departCode", value = "科室code", required = true)
            @PathVariable String departCode,
            @ApiParam(name = "workDate", value = "排班日期", required = true)
            @PathVariable String workDate) {
        return Result.ok(scheduleService.findList(hospitalCode, departCode, workDate));
    }

    @ApiOperation(value = "获取排班id获取排班数据")
    @GetMapping("findSchedule/{scheduleId}")
    public Result findScheduleById(@PathVariable String scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId);
        return Result.ok(schedule);
    }

    @ApiOperation(value = "根据排班id获取预约下单数据")
    @GetMapping("inner/findScheduleOrderVo/{scheduleId}")
    public ScheduleOrderVo findScheduleOrderVo(
            @ApiParam(name = "scheduleId", value = "排班id", required = true)
            @PathVariable("scheduleId") String scheduleId) {
        return scheduleService.findScheduleOrderVoById(scheduleId);
    }

    @ApiOperation(value = "获取医院签名信息")
    @GetMapping("inner/findSignInfoVo/{hospitalCode}")
    public SignInfoVo findSignInfoVo(
            @ApiParam(name = "hospitalCode", value = "医院code", required = true)
            @PathVariable("hospitalCode") String hospitalCode) {
        return hospitalSettingService.findSignInfoVoByHospitalCode(hospitalCode);
    }

}
