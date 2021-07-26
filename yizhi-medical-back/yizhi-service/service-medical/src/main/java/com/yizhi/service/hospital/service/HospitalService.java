package com.yizhi.service.hospital.service;

import com.yizhi.models.model.medical.Hospital;
import com.yizhi.models.vo.hospital.HospitalQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface HospitalService {
    //上传医院接口
    void save(Map<String, Object> paramMap);

    //实现根据医院编号查询
    Hospital findByHospitalCode(String hospitalCode);

    //医院列表(条件查询分页)
    Page<Hospital> findPage(Integer page, Integer limit, HospitalQueryVo hospitalQueryVo);

    //更新医院上线状态
    void updateStatusById(String id, Integer status);

    //医院详情信息
    Map<String, Object> findById(String id);

    //获取医院名称
    String getHospitalNameByHospitalCode(String hospitalCode);

    //根据医院名称查询
    List<Hospital> findListByHospitalNameLike(String hospitalName);

    //根据医院编号获取医院预约挂号详情
    Map<String, Object> findItemByHospitalCode(String hospitalCode);
}
