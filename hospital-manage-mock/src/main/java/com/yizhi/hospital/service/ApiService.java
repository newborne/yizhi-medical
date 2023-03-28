package com.yizhi.hospital.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Map;

/**
 * The interface Api service.
 */
public interface ApiService {

    /**
     * Gets hospital code.
     *
     * @return the hospital code
     */
    String getHospitalCode();

    /**
     * Gets sign key.
     *
     * @return the sign key
     */
    String getSignKey();

    /**
     * Gets hospital.
     *
     * @return the hospital
     */
    JSONObject getHospital();

    /**
     * Save hospital boolean.
     *
     * @param data the data
     * @return the boolean
     */
    boolean saveHospital(String data);

    /**
     * Find department map.
     *
     * @param pageNum  the page num
     * @param pageSize the page size
     * @return the map
     */
    Map<String, Object> findDepartment(int pageNum, int pageSize);

    /**
     * Save department boolean.
     *
     * @param data the data
     * @return the boolean
     */
    boolean saveDepartment(String data);

    /**
     * Remove department boolean.
     *
     * @param departCode the depart code
     * @return the boolean
     */
    boolean removeDepartment(String departCode);

    /**
     * Find schedule map.
     *
     * @param pageNum  the page num
     * @param pageSize the page size
     * @return the map
     */
    Map<String, Object> findSchedule(int pageNum, int pageSize);

    /**
     * Save schedule boolean.
     *
     * @param data the data
     * @return the boolean
     */
    boolean saveSchedule(String data);

    /**
     * Remove schedule boolean.
     *
     * @param hospitalScheduleId the hospital schedule id
     * @return the boolean
     */
    boolean removeSchedule(String hospitalScheduleId);

    /**
     * Save batch hospital.
     *
     * @throws IOException the io exception
     */
    void saveBatchHospital() throws IOException;
}
