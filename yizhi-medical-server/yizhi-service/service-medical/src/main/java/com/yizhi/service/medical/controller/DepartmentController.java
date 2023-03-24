package com.yizhi.service.medical.controller;

import com.yizhi.common.util.result.Result;
import com.yizhi.models.vo.medical.DepartmentVo;
import com.yizhi.service.medical.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "科室")
@RestController
@RequestMapping("/admin/medical/department")
//@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //根据医院编号，查询医院所有科室列表
    @ApiOperation(value = "查-by-hospitalCode")
    @GetMapping("tree/{hospitalCode}")
    public Result findTree(@PathVariable String hospitalCode) {
        List<DepartmentVo> list = departmentService.findTree(hospitalCode);
        return Result.ok(list);
    }
}
