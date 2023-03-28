package com.yizhi.model.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "签名信息")
public class SignInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "接口地址")
    private String apiUrl;

    @ApiModelProperty(value = "签名秘钥")
    private String signKey;
}

