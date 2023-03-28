package com.yizhi.util.common.exception;

import com.yizhi.util.common.result.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "自定义全局异常类")
public class YizhiException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    public YizhiException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public YizhiException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "YizhiException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}
