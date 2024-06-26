package com.yizhi.service.medical.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yizhi.model.pojo.medical.Hospital;
import com.yizhi.model.vo.medical.HospitalQueryVo;
import com.yizhi.service.dictionary.client.DictionaryFeignClient;
import com.yizhi.service.medical.repository.HospitalRepository;
import com.yizhi.service.medical.service.HospitalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private DictionaryFeignClient dictionaryFeignClient;

    @Override
    public void save(Map<String, Object> paramMap) {
        //把参数map集合转换对象 Hospital
        String mapString = JSONObject.toJSONString(paramMap);
        Hospital hospital = JSONObject.parseObject(mapString, Hospital.class);
        //判断是否存在数据
        String hospitalCode = hospital.getHospitalCode();
        Hospital hospitalExist = hospitalRepository.findByHospitalCode(hospitalCode);
        //如果存在，进行修改
        if (hospitalExist != null) {
            hospital.setStatus(hospitalExist.getStatus());
            hospital.setCreateTime(hospitalExist.getCreateTime());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        } else {//如果不存在，进行添加
            hospital.setStatus(0);
            hospital.setCreateTime(new Date());
            hospital.setUpdateTime(new Date());
            hospital.setIsDeleted(0);
            hospitalRepository.save(hospital);
        }
    }

    @Override
    public Hospital findByHospitalCode(String hospitalCode) {
        Hospital hospital = hospitalRepository.findByHospitalCode(hospitalCode);
        return hospital;
    }

    //医院列表(条件查询分页)
    @Override
    public Page<Hospital> findPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo) {
        //创建pageable对象
        Pageable pageable = PageRequest.of(page - 1, limit);
        //创建条件匹配器
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        //hospitalSetQueryVo转换Hospital对象
        Hospital hospital = new Hospital();
        BeanUtils.copyProperties(hospitalQueryVo, hospital);
        Example<Hospital> example = Example.of(hospital, matcher);
        //调用方法实现查询
        Page<Hospital> pages = hospitalRepository.findAll(example, pageable);
        //获取查询list集合，遍历进行医院等级封装
        pages.getContent().stream().forEach(item -> {
            this.putHospitalTypeString(item);
        });
        return pages;
    }

    //更新医院上线状态
    @Override
    public void updateStatusById(String id, Integer status) {
        //根据id查询医院信息
        Hospital hospital = hospitalRepository.findById(id).get();
        //设置修改的值
        hospital.setStatus(status);
        hospital.setUpdateTime(new Date());
        hospitalRepository.save(hospital);
    }

    @Override
    public Map<String, Object> findById(String id) {
        Map<String, Object> result = new HashMap<>();
        Hospital hospital = this.putHospitalTypeString(hospitalRepository.findById(id).get());
        //医院基本信息（包含医院等级）
        result.put("hospital", hospital);
        //单独处理更直观
        result.put("bookingRule", hospital.getBookingRule());
        //不需要重复返回
        hospital.setBookingRule(null);
        return result;
    }

    //获取医院名称
    @Override
    public String getHospitalNameByHospitalCode(String hospitalCode) {
        Hospital hospital = hospitalRepository.findByHospitalCode(hospitalCode);
        if (hospital != null) {
            return hospital.getHospitalName();
        }
        return null;
    }

    //根据医院名称查询
    @Override
    public List<Hospital> findListByHospitalNameLike(String hospitalName) {
        return hospitalRepository.findAllByHospitalNameLike(hospitalName);
    }

    //根据医院编号获取医院预约挂号详情
    @Override
    public Map<String, Object> findItemByHospitalCode(String hospitalCode) {
        Map<String, Object> result = new HashMap<>();
        //医院详情
        Hospital hospital = this.putHospitalTypeString(this.findByHospitalCode(hospitalCode));
        result.put("hospital", hospital);
        //预约规则
        result.put("bookingRule", hospital.getBookingRule());
        //不需要重复返回
        hospital.setBookingRule(null);
        return result;
    }

    //获取查询list集合，遍历进行医院等级封装
    private Hospital putHospitalTypeString(Hospital hospital) {
        //根据dictionaryCode和value获取医院等级名称
        String hospitalTypeString = dictionaryFeignClient.getName("HospitalType", hospital.getHospitalType());
        //查询省 市  地区
        String provinceString = dictionaryFeignClient.getName(hospital.getProvinceCode());
        String cityString = dictionaryFeignClient.getName(hospital.getCityCode());
        String districtString = dictionaryFeignClient.getName(hospital.getDistrictCode());
        hospital.getParam().put("fullAddress", provinceString + cityString + districtString);
        hospital.getParam().put("hospitalTypeString", hospitalTypeString);
        return hospital;
    }
}
