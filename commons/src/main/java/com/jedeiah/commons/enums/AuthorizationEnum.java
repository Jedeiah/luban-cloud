package com.jedeiah.commons.enums;



public enum AuthorizationEnum {
    //jwt token
    JWT_TOKEN("AuthorizationJwt");
    public final String value;

    AuthorizationEnum(String value) {
        this.value = value;
    }
}
