package com.yizhi.service.hospital.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yizhi.common.util.result.Result;
import com.yizhi.model.hospital.HospitalSetting;
import com.yizhi.service.hospital.service.HospitalSettingService;
import com.yizhi.service.util.utils.MD5;
import com.yizhi.vo.hospital.HospitalSettingQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@Api(tags = "医院设置")
@RestController
@RequestMapping("/admin/hospital/hospitalSetting")
@CrossOrigin
public class HospitalSettingController {
    //注入service
    @Autowired
    private HospitalSettingService hospitalSettingService;

    @ApiOperation(value = "医院设置-添加")
    @PostMapping("saveHospitalSetting")
    public Result saveHospitalSetting(@RequestBody HospitalSetting hospitalSetting) {
        //设置状态 1 使用 0 不能使用
        hospitalSetting.setStatus(1);
        //签名秘钥
        Random random = new Random();
        hospitalSetting.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + random.nextInt(1000)));
        //调用service
        boolean save = hospitalSettingService.save(hospitalSetting);
        if (save) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "医院设置-逻辑删除")
    @DeleteMapping("{id}")
    public Result removeHospitalSetting(@PathVariable Long id) {
        boolean flag = hospitalSettingService.removeById(id);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "医院设置-批量删除")
    @DeleteMapping("batchRemove")
    public Result batchRemoveHospitalSetting(@RequestBody List<Long> idList) {
        hospitalSettingService.removeByIds(idList);
        return Result.ok();
    }

    @ApiOperation(value = "医院设置-修改")
    @PostMapping("updateHospitalSetting")
    public Result updateHospitalSetting(@RequestBody HospitalSetting hospitalSetting) {
        boolean flag = hospitalSettingService.updateById(hospitalSetting);
        if (flag) {
            return Result.ok();
        } else {
            return Result.fail();
        }
    }

    @ApiOperation(value = "医院设置-状态锁定")
    @PutMapping("lockHospitalSetting/{id}/{status}")
    public Result lockHospitalSet(@PathVariable Long id,
                                  @PathVariable Integer status) {
        //根据id查询医院设置信息
        HospitalSetting hospitalSetting = hospitalSettingService.getById(id);
        //设置状态
        hospitalSetting.setStatus(status);
        //调用方法
        hospitalSettingService.updateById(hospitalSetting);
        return Result.ok();
    }

    @ApiOperation(value = "医院设置-发送密钥")
    @PutMapping("sendKey/{id}")
    public Result lockHospitalSetting(@PathVariable Long id) {
        HospitalSetting hospitalSetting = hospitalSettingService.getById(id);
        String signKey = hospitalSetting.getSignKey();
        String hospitalCode = hospitalSetting.getHospitalCode();
        //TODO 发送短信
        return Result.ok();
    }

    @ApiOperation(value = "医院设置-获取所有")
    @GetMapping("findAll")
    public Result findAllHospitalSetting() {
        //调用service的方法
        List<HospitalSetting> list = hospitalSettingService.list();
        return Result.ok(list);
    }

    @ApiOperation(value = "医院设置-条件查询和分页")
    @PostMapping("findPageHospitalSetting/{current}/{limit}")
    public Result findPageHospitalSetting(@PathVariable long current,
                                          @PathVariable long limit,
                                          @RequestBody(required = false) HospitalSettingQueryVo hospitalSettingQueryVo) {
        //创建page对象，传递当前页，每页记录数
        Page<HospitalSetting> page = new Page<>(current, limit);
        //构建条件
        QueryWrapper<HospitalSetting> wrapper = new QueryWrapper<>();
        String hospitalName = hospitalSettingQueryVo.getHospitalName();
        String hospitalCode = hospitalSettingQueryVo.getHospitalCode();
        if (!StringUtils.isEmpty(hospitalName)) {
            wrapper.like("hospital_name", hospitalSettingQueryVo.getHospitalName());
        }
        if (!StringUtils.isEmpty(hospitalCode)) {
            wrapper.eq("hospital_code", hospitalSettingQueryVo.getHospitalCode());
        }
        //调用方法实现分页查询
        Page<HospitalSetting> pageHospitalSetting = hospitalSettingService.page(page, wrapper);

        //返回结果
        return Result.ok(pageHospitalSetting);
    }

    @ApiOperation(value = "医院设置-根据id查找")
    @GetMapping("getHospitalSetting/{id}")
    public Result getHospitalSettingting(@PathVariable Long id) {
        HospitalSetting hospitalSetting = hospitalSettingService.getById(id);
        return Result.ok(hospitalSetting);
    }
}
