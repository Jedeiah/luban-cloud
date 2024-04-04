package com.jedeiah.commons.enums;


public enum HeaderParamEnum {
    USER_ID("userId"),
    LOGIN_TYPE("loginType");

    public final String value;

    HeaderParamEnum(String value) {
        this.value = value;
    }
}
