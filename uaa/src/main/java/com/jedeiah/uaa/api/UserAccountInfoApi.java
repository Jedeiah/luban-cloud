package com.jedeiah.uaa.api;

import com.jedeiah.commons.enums.PermissionEnum;
import com.jedeiah.uaa.api.feign.UserAccountInfoRemote;
import com.jedeiah.uaa.service.UserAccountInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "用户账户信息相关 api")
public class UserAccountInfoApi implements UserAccountInfoRemote {

    @Autowired
    private UserAccountInfoService userAccountInfoService;
    @Operation(summary = "查看用户是否具有权限")
    @Override
    public boolean hasPermission(String userId, PermissionEnum permission) {
        return userAccountInfoService.hasPermission(userId,permission);
    }
}
