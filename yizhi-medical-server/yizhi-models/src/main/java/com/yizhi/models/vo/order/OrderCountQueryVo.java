package com.yizhi.models.vo.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "OrderCountQueryVo")
public class OrderCountQueryVo {
	
	@ApiModelProperty(value = "医院编号")
	private String hospitalCode;

	@ApiModelProperty(value = "医院名称")
	private String hospitalName;

	@ApiModelProperty(value = "安排日期")
	private String reserveDateBegin;
	private String reserveDateEnd;

}

