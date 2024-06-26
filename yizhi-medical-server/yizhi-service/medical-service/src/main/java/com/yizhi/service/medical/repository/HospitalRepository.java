package com.yizhi.service.medical.repository;

import com.yizhi.model.pojo.medical.Hospital;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalRepository extends MongoRepository<Hospital, String> {
    //判断是否存在数据
    Hospital findByHospitalCode(String hospitalCode);

    //根据医院名称查询
    List<Hospital> findAllByHospitalNameLike(String hospitalName);
}
