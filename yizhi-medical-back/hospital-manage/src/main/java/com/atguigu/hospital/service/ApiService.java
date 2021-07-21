package com.atguigu.hospital.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Map;

public interface ApiService {

    String getHospitalCode();

    String getSignKey();

    JSONObject getHospital();

    boolean saveHospital(String data);

    Map<String, Object> findDepartment(int pageNum, int pageSize);

    boolean saveDepartment(String data);

    boolean removeDepartment(String departCode);

    Map<String, Object> findSchedule(int pageNum, int pageSize);

    boolean saveSchedule(String data);

    boolean removeSchedule(String hospitalScheduleId);

    void  saveBatchHospital() throws IOException;
}
