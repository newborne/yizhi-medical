package com.yizhi.service.hospital.service;


import com.yizhi.models.model.hospital.Department;
import com.yizhi.models.vo.hospital.DepartmentQueryVo;
import com.yizhi.models.vo.hospital.DepartmentVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    //上传科室接口
    void save(Map<String, Object> paramMap);

    //查询科室接口
    Page<Department> findAll(int page, int limit, DepartmentQueryVo departmentQueryVo);

    //删除科室接口
    void delete(String hospitalCode, String departCode);

    //根据医院编号，查询医院所有科室列表
    List<DepartmentVo> findDepartmentTree(String hospitalCode);

    //根据科室编号，和医院编号，查询科室名称
    String getDepartName(String hospitalCode, String departCode);

    //根据科室编号，和医院编号，查询科室
    Department findDepartment(String hospitalCode, String departCode);
}
