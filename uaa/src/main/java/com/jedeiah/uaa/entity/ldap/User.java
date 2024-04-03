package com.jedeiah.uaa.entity.ldap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entry(base = "ou=People,dc=jedeiah,dc=com", objectClasses = "inetOrgPerson")
public class User {

    @Id
    private Name id;
    @Attribute(name = "uid")
    private String uid;
    @Attribute(name = "cn")
    private String username;
    @Attribute(name = "sn")
    private String surName;
    @Attribute(name = "userPassword")
    private String userPassword;
}