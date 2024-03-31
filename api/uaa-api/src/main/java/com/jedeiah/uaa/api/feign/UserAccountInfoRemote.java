package com.jedeiah.uaa.api.feign;

import com.jedeiah.commons.enums.PermissionEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("uaa")
public interface UserAccountInfoRemote {

    /**
     *  查看用户是否具有指定权限
     * @param userId
     * @param permission
     * @return
     */
    @GetMapping(value = "/users/feign/isPermission")
    boolean hasPermission(@RequestParam("userId") String userId,@RequestParam("permission") PermissionEnum permission);

}
