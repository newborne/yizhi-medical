package com.yizhi.service.medical.controller.admin;

import com.yizhi.model.vo.medical.DepartmentVo;
import com.yizhi.service.medical.service.DepartmentService;
import com.yizhi.util.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
