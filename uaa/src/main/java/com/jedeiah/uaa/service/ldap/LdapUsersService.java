package com.jedeiah.uaa.service.ldap;

import com.jedeiah.commons.enums.PermissionEnum;
import com.jedeiah.commons.vo.RespVo;

public interface LdapUsersService {
    RespVo login(String username, String password);

    boolean hasPermission(String userId, PermissionEnum permission);
}
