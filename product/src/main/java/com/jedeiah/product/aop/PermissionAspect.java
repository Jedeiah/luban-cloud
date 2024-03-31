package com.jedeiah.product.aop;

import com.jedeiah.commons.enums.PermissionEnum;
import com.jedeiah.uaa.api.feign.UserAccountInfoRemote;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class PermissionAspect {

    @Autowired
    private UserAccountInfoRemote userAccountInfoRemote;

    @Before("@annotation(permissionRequired)")
    public void checkPermission(JoinPoint joinPoint, PermissionRequired permissionRequired) throws Exception {
        // 获取当前请求中的 user_id
        String userId = getCurrentUserIdFromHeader();

        // 根据 userId 和操作类型查询数据库权限
        PermissionEnum operation = permissionRequired.operation();
        boolean hasPermission = userAccountInfoRemote.hasPermission(userId, operation);

        Assert.isTrue(hasPermission, "您没有权限进行此操作！");
    }

    private String getCurrentUserIdFromHeader() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(attributes, "系统异常");
        HttpServletRequest request = attributes.getRequest();
        // 从请求头中获取 user_id
        String userId = request.getHeader("userId");
        Assert.isTrue(StringUtils.hasLength(userId), "userId不能为空");
        return userId;
    }
}
