package com.jedeiah.commons.enums;



public enum HeaderParamEnum {
    //jwt token
    USER_ID("userId"),
    LOGIN_TYPE("loginType");

    private final String name;

    HeaderParamEnum(String name) {
        this.name = name;
    }
}
