package com.jedeiah.commons.enums;



public enum AuthorizationEnum {
    //jwt token
    JWT_TOKEN("AuthorizationJwt");
    private final String name;

    AuthorizationEnum(String name) {
        this.name = name;
    }
}
