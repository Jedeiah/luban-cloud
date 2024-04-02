package com.jedeiah.commons.enums;



public enum TokenEnum {
    //jwt token
    JWT_TOKEN("AuthorizationJwt");
    private final String name;

    TokenEnum(String name) {
        this.name = name;
    }
}
