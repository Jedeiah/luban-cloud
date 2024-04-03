package com.jedeiah.uaa.entity.ldap;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entry(base = "ou=Roles,dc=jedeiah,dc=com", objectClasses = "groupOfNames")
public class Role {

    @Id
    private Name id;
    @Attribute(name = "cn")
    private String roleName;
}