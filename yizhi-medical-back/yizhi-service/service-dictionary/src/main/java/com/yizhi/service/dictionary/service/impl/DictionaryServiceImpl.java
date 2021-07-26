package com.yizhi.service.dictionary.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yizhi.models.model.dictionary.Dictionary;
import com.yizhi.service.dictionary.listener.DictionaryListener;
import com.yizhi.service.dictionary.mapper.DictionaryMapper;
import com.yizhi.service.dictionary.service.DictionaryService;
import com.yizhi.models.vo.dictionary.DictionaryExcelVo;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    //根据数据id查询子数据列表
    @Override
    @Cacheable(value = "dictionary",keyGenerator = "keyGenerator")
    public List<Dictionary> findChlidrenListById(Long id) {
        QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Dictionary> dictionaryList = baseMapper.selectList(wrapper);
        //向list集合每个dictionary对象中设置hasChildren
        for (Dictionary dictionary:dictionaryList) {
            Long dictionaryId = dictionary.getId();
            boolean hasChildren = this.hasChildren(dictionaryId);
            dictionary.setHasChildren(hasChildren);
        }
        return dictionaryList;
    }

    //导出数据字典接口
    @Override
    public void exportData(HttpServletResponse response) {
        //设置下载信息
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileName = "dictionary";
        response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
        //查询数据库
        List<Dictionary> dictionaryList = baseMapper.selectList(null);
        //Dictionary -- DictionaryExcelVo
        List<DictionaryExcelVo> dictionaryVoList = new ArrayList<>();
        for(Dictionary dictionary:dictionaryList) {
            DictionaryExcelVo dictionaryExcelVo = new DictionaryExcelVo();
            // dictionaryExcelVo.setId(dictionary.getId());
            BeanUtils.copyProperties(dictionary,dictionaryExcelVo);
            dictionaryVoList.add(dictionaryExcelVo);
        }
        //调用方法进行写操作
        try {
            EasyExcel.write(response.getOutputStream(), DictionaryExcelVo.class).sheet("dictionary")
                    .doWrite(dictionaryVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //导入数据字典
    @Override
    @CacheEvict(value = "dictionary", allEntries=true)
    public void importData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),DictionaryExcelVo.class,new DictionaryListener(baseMapper)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //根据dictionarycode和value查询
    @Override
    public String getName(String dictionaryCode, String value) {
        //如果dictionaryCode为空，直接根据value查询
        if(StringUtils.isEmpty(dictionaryCode)) {
            //直接根据value查询
            QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
            wrapper.eq("value",value);
            Dictionary dictionary = baseMapper.selectOne(wrapper);
            return dictionary.getName();
        } else {//如果dictionaryCode不为空，根据dictionaryCode和value查询
            //根据dictionarycode查询dictionary对象，得到dictionary的id值
            Dictionary codeDictionary = this.findByCode(dictionaryCode);
            Long parent_id = codeDictionary.getId();
            //根据parent_id和value进行查询
            Dictionary finalDictionary = baseMapper.selectOne(new QueryWrapper<Dictionary>()
                    .eq("parent_id", parent_id)
                    .eq("value", value));
            return finalDictionary.getName();
        }
    }

    //根据dictionaryCode获取下级节点
    @Override
    public List<Dictionary> findChildrenListByCode(String dictionaryCode) {
        //根据dictionarycode获取对应id
        Dictionary dictionary = this.findByCode(dictionaryCode);
        //根据id获取子节点
        List<Dictionary> chlid= this.findChlidrenListById(dictionary.getId());
        return chlid;
    }

    private Dictionary findByCode(String dictionaryCode) {
        QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("dictionary_code",dictionaryCode);
        Dictionary dictionary = baseMapper.selectOne(wrapper);
        return dictionary;
    }

    //判断id下面是否有子节点
    private boolean hasChildren(Long id) {
        QueryWrapper<Dictionary> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(wrapper);
        // 0>0    1>0
        return count>0;
    }
}
