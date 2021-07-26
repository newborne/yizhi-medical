package com.yizhi.service.hospital.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yizhi.models.model.medical.HospitalSetting;
import com.yizhi.models.vo.order.SignInfoVo;

public interface HospitalSettingService extends IService<HospitalSetting> {

    //2 根据传递过来医院编码，查询数据库，查询签名
    String getSignKeyByHospitalCode(String hospitalCode);

    //获取医院签名信息
    SignInfoVo findSignInfoVoByHospitalCode(String hospitalCode);
}
