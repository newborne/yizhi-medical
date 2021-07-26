package com.yizhi.service.dictionary.controller;


import com.yizhi.common.util.result.Result;
import com.yizhi.models.model.dictionary.Dictionary;
import com.yizhi.service.dictionary.service.DictionaryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "数据字典")
@RestController
@RequestMapping("/admin/dictionary")
@CrossOrigin
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    //导入数据字典
    @ApiOperation(value = "导入")
    @PostMapping("importData")
    public Result importDictionary(MultipartFile file) {
        dictionaryService.importDictionaryData(file);
        return Result.ok();
    }

    //导出数据字典
    @ApiOperation(value = "导出")
    @GetMapping("exportData")
    public void exportDictionary(HttpServletResponse response) {
        dictionaryService.exportDictionaryData(response);
    }

    //根据dictionaryCode获取下级节点
    @ApiOperation(value = "查子节点-by-dictionaryCode")
    @GetMapping("findByDictionaryCode/{dictionaryCode}")
    public Result findByDictionaryCode(@PathVariable String dictionaryCode) {
        List<Dictionary> list = dictionaryService.findByDictionaryCode(dictionaryCode);
        return Result.ok(list);
    }

    //根据数据id查询子数据列表
    @ApiOperation(value = "查子节点-by-id")
    @GetMapping("findChildData/{id}")
    public Result findChildData(@PathVariable Long id) {
        List<Dictionary> list = dictionaryService.findChlidData(id);
        return Result.ok(list);
    }

    //根据dictcode和value查询
    @ApiOperation(value = "查名称-by-dictionary&value")
    @GetMapping("getName/{dictionaryCode}/{value}")
    public String getName(@PathVariable String dictionaryCode,
                          @PathVariable String value) {
        String dictionaryName = dictionaryService.getDictionaryName(dictionaryCode,value);
        return dictionaryName;
    }

    //根据value查询
    @ApiOperation(value = "查名称-by-value")
    @GetMapping("getName/{value}")
    public String getName(@PathVariable String value) {
        String dictionaryName = dictionaryService.getDictionaryName("",value);
        return dictionaryName;
    }
}
