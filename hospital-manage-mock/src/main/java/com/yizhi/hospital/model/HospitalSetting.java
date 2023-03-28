package com.yizhi.hospital.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * The type Hospital setting.
 */
@Data
@ApiModel(description = "HospitalSetting")
@TableName("hospital_setting")
public class HospitalSetting extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "医院编号")
    private String hospitalCode;

    @ApiModelProperty(value = "签名秘钥")
    private String signKey;

    @ApiModelProperty(value = "api基础路径")
    private String apiUrl;
}

