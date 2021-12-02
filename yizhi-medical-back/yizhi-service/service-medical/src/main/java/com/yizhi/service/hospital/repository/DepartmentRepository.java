package com.yizhi.service.hospital.repository;


import com.yizhi.models.model.medical.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends MongoRepository<Department,String> {
    //上传科室接口
    Department findDepartmentByHospitalCodeAndDepartCode(String hospitalCode, String departCode);
}
