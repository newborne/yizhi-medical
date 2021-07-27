package com.yizhi.service.hospital.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yizhi.common.util.exception.YizhiException;
import com.yizhi.common.util.result.ResultCodeEnum;
import com.yizhi.models.model.hospital.HospitalSetting;
import com.yizhi.models.vo.order.SignInfoVo;
import com.yizhi.service.hospital.mapper.HospitalSettingMapper;
import com.yizhi.service.hospital.service.HospitalSettingService;
import org.springframework.stereotype.Service;

@Service
public class HospitalSettingServiceImpl extends ServiceImpl<HospitalSettingMapper, HospitalSetting> implements HospitalSettingService {
    //2 根据传递过来医院编码，查询数据库，查询签名
    @Override
    public String getSignKeyByHospitalCode(String hospitalCode) {
        QueryWrapper<HospitalSetting> wrapper = new QueryWrapper<>();
        wrapper.eq("hospital_code",hospitalCode);
        HospitalSetting hospitalSetting = baseMapper.selectOne(wrapper);
        return hospitalSetting.getSignKey();
    }

    @Override
    public SignInfoVo findSignInfoVoByHospitalCode(String hospitalCode) {
        QueryWrapper<HospitalSetting> wrapper = new QueryWrapper<>();
        wrapper.eq("hospital_code",hospitalCode);
        HospitalSetting hospitalSetting = baseMapper.selectOne(wrapper);
        if(null == hospitalSetting) {
            throw new YizhiException(ResultCodeEnum.HOSPITAL_OPEN);
        }
        SignInfoVo signInfoVo = new SignInfoVo();
        signInfoVo.setApiUrl(hospitalSetting.getApiUrl());
        signInfoVo.setSignKey(hospitalSetting.getSignKey());
        return signInfoVo;
    }
}
