package com.yizhi.service.dictionary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yizhi.model.pojo.dictionary.Dictionary;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface DictionaryService extends IService<Dictionary> {

    //根据数据id查询子数据列表
    List<Dictionary> findChlidrenListById(Long id);

    //导出数据字典接口
    void exportData(HttpServletResponse response);

    //导入数据字典
    void importData(MultipartFile file);

    //根据dictionaryCode和value查询
    String getName(String dictionaryCode, String value);

    //根据dictionaryCode获取下级节点
    List<Dictionary> findChildrenListByCode(String dictionaryCode);
}
