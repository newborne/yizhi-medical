package com.yizhi.service.hospital.service;


import com.yizhi.models.model.hospital.Schedule;
import com.yizhi.models.vo.hospital.ScheduleOrderVo;
import com.yizhi.models.vo.hospital.ScheduleQueryVo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ScheduleService {
    //上传排班接口
    void save(Map<String, Object> paramMap);

    //查询排班接口
    Page<Schedule> findAll(int page, int limit, ScheduleQueryVo scheduleQueryVo);

    //删除排班
    void delete(String hospitalCode, String hospitalScheduleId);

    //根据医院编号 和 科室编号 ，查询排班规则数据
    Map<String, Object> getBookingRule(long page, long limit, String hospitalCode, String departCode);

//    //根据医院编号 、科室编号和工作日期，查询排班详细信息
//    List<Schedule> findSchedule(String hospitalCode, String departCode, String workDate);
//
//    //获取可预约的排班数据
//    Map<String,Object> getBookingScheduleRule(int page, int limit, String hospitalCode, String departCode);
//
//    //获取排班id获取排班数据
//    Schedule getScheduleId(String scheduleId);
//
//    //根据排班id获取预约下单数据
//    ScheduleOrderVo getScheduleOrderVo(String scheduleId);
//
//    //更新排班数据 用于mp
//    void update(Schedule schedule);
}