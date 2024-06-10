package com.yizhi.service.dictionary.controller;

import com.yizhi.util.common.result.Result;
import com.yizhi.model.pojo.dictionary.Dictionary;
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
//@CrossOrigin
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    //导入数据字典
    @ApiOperation(value = "导入")
    @PostMapping("import")
    public Result importDictionary(MultipartFile file) {
        dictionaryService.importData(file);
        return Result.ok();
    }

    //导出数据字典
    @ApiOperation(value = "导出")
    @GetMapping("export")
    public void exportDictionary(HttpServletResponse response) {
        dictionaryService.exportData(response);
    }

    //根据dictionaryCode获取下级节点
    @ApiOperation(value = "查子节点-by-dictionaryCode")
    @GetMapping("children/dictionaryCode/{dictionaryCode}")
    public Result findChlidrenList(@PathVariable String dictionaryCode) {
        List<Dictionary> list = dictionaryService.findChildrenListByCode(dictionaryCode);
        return Result.ok(list);
    }

    //根据数据id查询子数据列表
    @ApiOperation(value = "查子节点-by-id")
    @GetMapping("children/id/{id}")
    public Result findChildrenList(@PathVariable Long id) {
        List<Dictionary> list = dictionaryService.findChlidrenListById(id);
        return Result.ok(list);
    }

    //根据dictionaryCode和value查询
    @ApiOperation(value = "查名称-by-dictionary&value")
    @GetMapping("name/{dictionaryCode}/{value}")
    public String getName(@PathVariable String dictionaryCode,
                          @PathVariable String value) {
        String dictionaryName = dictionaryService.getName(dictionaryCode, value);
        return dictionaryName;
    }

    //根据value查询
    @ApiOperation(value = "查名称-by-value")
    @GetMapping("name/{value}")
    public String getName(@PathVariable String value) {
        String dictionaryName = dictionaryService.getName("", value);
        return dictionaryName;
    }
}
