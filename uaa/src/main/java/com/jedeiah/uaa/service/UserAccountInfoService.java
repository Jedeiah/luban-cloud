package com.jedeiah.uaa.service;

import com.jedeiah.commons.enums.PermissionEnum;

public interface UserAccountInfoService {
    boolean hasPermission(String userId, PermissionEnum permission);
}
