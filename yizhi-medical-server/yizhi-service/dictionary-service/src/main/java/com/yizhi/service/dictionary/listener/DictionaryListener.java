package com.yizhi.service.dictionary.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.yizhi.model.pojo.dictionary.Dictionary;
import com.yizhi.service.dictionary.mapper.DictionaryMapper;
import com.yizhi.model.vo.dictionary.DictionaryExcelVo;
import org.springframework.beans.BeanUtils;

public class DictionaryListener extends AnalysisEventListener<DictionaryExcelVo> {

    private DictionaryMapper dictionaryMapper;

    public DictionaryListener(DictionaryMapper dictionaryMapper) {
        this.dictionaryMapper = dictionaryMapper;
    }

    //一行一行读取
    @Override
    public void invoke(DictionaryExcelVo dictionaryExcelVo, AnalysisContext analysisContext) {
        //调用方法添加数据库
        Dictionary dictionary = new Dictionary();
        BeanUtils.copyProperties(dictionaryExcelVo, dictionary);
        dictionaryMapper.insert(dictionary);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}
