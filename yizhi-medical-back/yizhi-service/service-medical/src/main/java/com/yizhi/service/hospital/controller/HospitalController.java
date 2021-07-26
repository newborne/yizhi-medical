package com.yizhi.service.hospital.controller;


import com.yizhi.common.util.result.Result;
import com.yizhi.models.model.hospital.Hospital;
import com.yizhi.models.vo.hospital.HospitalQueryVo;
import com.yizhi.service.hospital.service.HospitalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "医院")
@RestController
@RequestMapping("/admin/medical/hospital")
//@CrossOrigin
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    //医院列表(条件查询分页)
    @ApiOperation(value = "查-分页")
    @GetMapping("list/{page}/{limit}")
    public Result listHospital(@PathVariable Integer page,
                           @PathVariable Integer limit,
                           HospitalQueryVo hospitalQueryVo) {
        Page<Hospital> pageModel = hospitalService.findAll(page,limit,hospitalQueryVo);
        List<Hospital> content = pageModel.getContent();
        long totalElements = pageModel.getTotalElements();

        return Result.ok(pageModel);
    }

    //更新医院上线状态
    @ApiOperation(value = "改-状态")
    @GetMapping("updateHospitalStatus/{id}/{status}")
    public Result updateHospitalStatus(@PathVariable String id,@PathVariable Integer status) {
        hospitalService.updateStatusById(id,status);
        return Result.ok();
    }

    //医院详情信息
    @ApiOperation(value = "查-详情-by-id")
    @GetMapping("showHospitalDetail/{id}")
    public Result showHospitalDetail(@PathVariable String id) {
        Map<String, Object> map = hospitalService.findById(id);
        return Result.ok(map);
    }
}
