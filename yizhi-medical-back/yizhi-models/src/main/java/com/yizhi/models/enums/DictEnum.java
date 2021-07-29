package com.yizhi.models.enums;

public enum DictEnum {


    HOSTYPE("Hostype", "医院等级"),
    CERTIFICATES_TYPE("CertificatesType", "证件类型"),
    ;

    private String dictionaryCode;
    private String msg;

    DictEnum(String dictionaryCode, String msg) {
        this.dictionaryCode = dictionaryCode;
        this.msg = msg;
    }

    public String getDictionaryCode() {
        return dictionaryCode;
    }

    public void setDictionaryCode(String dictionaryCode) {
        this.dictionaryCode = dictionaryCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
