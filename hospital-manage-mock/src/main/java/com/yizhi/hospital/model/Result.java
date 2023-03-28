package com.yizhi.hospital.model;

import com.yizhi.hospital.enums.ResultCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * The type Result.
 *
 * @param <T> the type parameter
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    /**
     * Instantiates a new Result.
     */
    public Result() {
    }

    /**
     * Build result.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the result
     */
    public static <T> Result<T> build(T data) {
        Result<T> result = new Result<T>();
        if (data != null)
            result.setData(data);
        return result;
    }

    /**
     * Build result.
     *
     * @param <T>            the type parameter
     * @param body           the body
     * @param resultCodeEnum the result code enum
     * @return the result
     */
    public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
        Result<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }

    /**
     * Ok result.
     *
     * @param <T> the type parameter
     * @return the result
     */
    public static <T> Result<T> ok() {
        return Result.ok(null);
    }

    /**
     * Ok result.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the result
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.SUCCESS);
    }

    /**
     * Fail result.
     *
     * @param <T> the type parameter
     * @return the result
     */
    public static <T> Result<T> fail() {
        return Result.fail(null);
    }

    /**
     * Fail result.
     *
     * @param <T>  the type parameter
     * @param data the data
     * @return the result
     */
    public static <T> Result<T> fail(T data) {
        Result<T> result = build(data);
        return build(data, ResultCodeEnum.FAIL);
    }

    /**
     * Message result.
     *
     * @param msg the msg
     * @return the result
     */
    public Result<T> message(String msg) {
        this.setMessage(msg);
        return this;
    }

    /**
     * Code result.
     *
     * @param code the code
     * @return the result
     */
    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }
}
