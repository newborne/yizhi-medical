package com.yizhi.service.medical.client;


import com.yizhi.models.vo.medical.ScheduleOrderVo;
import com.yizhi.models.vo.order.SignInfoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-medical")
@Repository
public interface MedicalFeignClient {

    /**
     * 根据排班id获取预约下单数据
     */
    @GetMapping("/api/medical/hospital/inner/getScheduleOrderVo/{scheduleId}")
    ScheduleOrderVo getScheduleOrderVo(@PathVariable("scheduleId") String scheduleId);

    /**
     * 获取医院签名信息
     */
    @GetMapping("/api/medical/hospital/inner/getSignInfoVo/{hospitalCode}")
    public SignInfoVo getSignInfoVo(@PathVariable("hospitalCode") String hospitalCode);
}
