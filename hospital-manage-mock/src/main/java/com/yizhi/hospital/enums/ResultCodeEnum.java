package com.yizhi.hospital.enums;

import lombok.Getter;

/**
 * The enum Result code enum.
 */
@Getter
public enum ResultCodeEnum {

    /**
     * Success result code enum.
     */
    SUCCESS(200, "成功"),
    /**
     * Fail result code enum.
     */
    FAIL(201, "失败"),
    /**
     * Service error result code enum.
     */
    SERVICE_ERROR(202, "服务异常"),
    /**
     * Data error result code enum.
     */
    DATA_ERROR(204, "数据异常"),
    /**
     * Sign error result code enum.
     */
    SIGN_ERROR(300, "签名错误"),
    /**
     * Pay password error result code enum.
     */
    PAY_PASSWORD_ERROR(401, "支付密码错误"),
    /**
     * Repeat error result code enum.
     */
    REPEAT_ERROR(402, "重复提交"),
    /**
     * Invest ammount more error result code enum.
     */
    INVEST_AMMOUNT_MORE_ERROR(501, "出借金额已经多余标的金额"),
    /**
     * Return ammount more error result code enum.
     */
    RETURN_AMMOUNT_MORE_ERROR(502, "还款金额不正确"),
    /**
     * Project ammount error result code enum.
     */
    PROJECT_AMMOUNT_ERROR(503, "标的金额不一致");

    private Integer code;

    private String message;

    private ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
