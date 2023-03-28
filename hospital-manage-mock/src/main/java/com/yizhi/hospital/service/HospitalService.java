package com.yizhi.hospital.service;

import java.io.IOException;
import java.util.Map;

/**
 * The interface Hospital service.
 */
public interface HospitalService {

    /**
     * Submit order map.
     *
     * @param paramMap the param map
     * @return the map
     */
    Map<String, Object> submitOrder(Map<String, Object> paramMap);

    /**
     * Update pay status.
     *
     * @param paramMap the param map
     */
    void updatePayStatus(Map<String, Object> paramMap);

    /**
     * Update cancel status.
     *
     * @param paramMap the param map
     */
    void updateCancelStatus(Map<String, Object> paramMap);
}
