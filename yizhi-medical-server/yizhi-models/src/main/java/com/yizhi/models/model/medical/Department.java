package com.yizhi.models.model.medical;

import com.yizhi.models.model.base.BaseMongoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ApiModel(description = "Department")
@Document("Department")
public class Department extends BaseMongoEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "医院编号")
	@Indexed //普通索引
	private String hospitalCode;

	@ApiModelProperty(value = "科室编号")
	@Indexed(unique = true) //唯一索引
	private String departCode;

	@ApiModelProperty(value = "科室名称")
	private String departName;

	@ApiModelProperty(value = "科室描述")
	private String intro;

	@ApiModelProperty(value = "大科室编号")
	private String bigDepartCode;

	@ApiModelProperty(value = "大科室名称")
	private String bigDepartName;

}

