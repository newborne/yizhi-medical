package com.yizhi.hospital.exception;

import com.yizhi.hospital.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * The type Yizhi exception.
 */
@Data
@ApiModel(value = "自定义全局异常类")
public class YizhiException extends RuntimeException {

    @ApiModelProperty(value = "异常状态码")
    private Integer code;

    /**
     * Instantiates a new Yizhi exception.
     *
     * @param message the message
     * @param code    the code
     */
    public YizhiException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * Instantiates a new Yizhi exception.
     *
     * @param resultCodeEnum the result code enum
     */
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
