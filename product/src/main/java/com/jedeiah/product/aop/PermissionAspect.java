package com.jedeiah.product.aop;

import com.jedeiah.commons.enums.HeaderParamEnum;
import com.jedeiah.commons.enums.LoginTypeEnum;
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
    public void checkPermission(JoinPoint joinPoint, PermissionRequired permissionRequired){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Assert.notNull(attributes, "系统异常");
        HttpServletRequest request = attributes.getRequest();
        // 从请求头中获取 user_id
        String userId = request.getHeader(HeaderParamEnum.USER_ID.value);
        Assert.isTrue(StringUtils.hasLength(userId), "userId不能为空");
        LoginTypeEnum loginType = LoginTypeEnum.valueOf(request.getHeader(HeaderParamEnum.LOGIN_TYPE.value));
        Assert.notNull(loginType, "loginType不能为空");
        PermissionEnum operation = permissionRequired.operation();
        boolean hasPermission = switch (loginType) {
            case USER -> userAccountInfoRemote.hasPermission(userId, operation);
            case LDAP -> userAccountInfoRemote.ldapHasPermission(userId, operation);
        };
        if (hasPermission) {
            return;
        }
        Assert.isTrue(PermissionEnum.CREATE != operation, "您没有新增权限！");
        Assert.isTrue(PermissionEnum.DELETE != operation, "您没有删除权限！");
        Assert.isTrue(PermissionEnum.UPDATE != operation, "您没有修改权限！");
        Assert.isTrue(PermissionEnum.READ != operation, "您没有查询权限！");

    }
}
