package com.yizhi.models.model.medical;

import com.yizhi.models.model.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "医院设置")
@TableName("hospital_setting")
public class HospitalSetting extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "医院名称")
	@TableField("hospital_name")
	private String hospitalName;

	@ApiModelProperty(value = "医院编号")
	@TableField("hospital_code")
	private String hospitalCode;

	@ApiModelProperty(value = "接口地址")
	@TableField("api_url")
	private String apiUrl;

	@ApiModelProperty(value = "签名秘钥")
	@TableField("sign_key")
	private String signKey;

	@ApiModelProperty(value = "联系人姓名")
	@TableField("linkman_name")
	private String linkmanName;

	@ApiModelProperty(value = "联系人手机")
	@TableField("linkman_phone")
	private String linkmanPhone;

	@ApiModelProperty(value = "状态")
	@TableField("status")
	private Integer status;

}

