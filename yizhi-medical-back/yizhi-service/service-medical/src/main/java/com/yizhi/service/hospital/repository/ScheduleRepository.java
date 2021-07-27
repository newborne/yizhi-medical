package com.yizhi.service.hospital.repository;

import com.yizhi.models.model.hospital.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule,String> {
    //根据医院编号 和 排班编号查询
    Schedule getScheduleByHospitalCodeAndHospitalScheduleId(String hospitalCode, String hospitalScheduleId);

    //根据医院编号 、科室编号和工作日期，查询排班详细信息
    List<Schedule> findScheduleByHospitalCodeAndDepartCodeAndWorkDate(String hospitalCode, String departCode, Date toDate);
}
