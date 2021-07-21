package com.yizhi.service.hospital.controller;

import com.yizhi.common.util.result.Result;
import com.yizhi.models.model.hospital.Schedule;
import com.yizhi.service.hospital.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "排班")
@RestController
@RequestMapping("/admin/medical/schedule")
@CrossOrigin
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    //根据医院编号 和 科室编号 ，查询排班规则数据
    @ApiOperation(value ="查-分页-by-hospitalCode&departCode")
    @GetMapping("getScheduleRule/{page}/{limit}/{hospitalCode}/{departCode}")
    public Result getScheduleRule(@PathVariable long page,
                                  @PathVariable long limit,
                                  @PathVariable String hospitalCode,
                                  @PathVariable String departCode) {
        Map<String,Object> map = scheduleService.getBookingRule(page,limit,hospitalCode,departCode);
        return Result.ok(map);
    }

//    //根据医院编号 、科室编号和工作日期，查询排班详细信息
//    @ApiOperation(value = "查-详情-by-hospitalCode&departCode&workDate")
//    @GetMapping("getScheduleDetail/{hospitalCode}/{departCode}/{workDate}")
//    public Result getScheduleDetail( @PathVariable String hospitalCode,
//                                     @PathVariable String departCode,
//                                     @PathVariable String workDate) {
//        List<Schedule> list = scheduleService.getDetailSchedule(hospitalCode,departCode,workDate);
//        return Result.ok(list);
//    }
}
