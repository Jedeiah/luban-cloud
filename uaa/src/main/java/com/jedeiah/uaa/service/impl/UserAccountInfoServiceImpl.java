package com.jedeiah.uaa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jedeiah.commons.enums.PermissionEnum;
import com.jedeiah.commons.utls.JwtTokenUtil;
import com.jedeiah.commons.vo.RespVo;
import com.jedeiah.uaa.entity.RolePermissions;
import com.jedeiah.uaa.entity.UserRoles;
import com.jedeiah.uaa.entity.Users;
import com.jedeiah.uaa.mapper.PermissionsMapper;
import com.jedeiah.uaa.mapper.RolePermissionsMapper;
import com.jedeiah.uaa.mapper.UserRolesMapper;
import com.jedeiah.uaa.mapper.UsersMapper;
import com.jedeiah.uaa.service.UserAccountInfoService;
import com.jedeiah.uaa.service.UsersService;
import com.jedeiah.uaa.utils.RedisUtil;
import com.jedeiah.uaa.vo.UsersVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserAccountInfoServiceImpl implements UserAccountInfoService {

    @Autowired
    PermissionsMapper permissionsMapper;
    @Autowired
    UserRolesMapper userRolesMapper;

    @Autowired
    RolePermissionsMapper rolePermissionsMapper;
    @Autowired
    RedisUtil redisUtil;


    @Override
    public boolean hasPermission(String userId, PermissionEnum permission) {
        String redisKey = "permission:" + userId;
        List<PermissionEnum> values = (List<PermissionEnum>) redisUtil.get(redisKey);
        if (values != null) {
            return values.contains(permission);
        }

        List<UserRoles> userRolesList = userRolesMapper.selectList(
                new LambdaQueryWrapper<UserRoles>().eq(UserRoles::getUserId, userId)
                        .eq(UserRoles::isDeleted, false));
        if (CollectionUtils.isEmpty(userRolesList)) {
            return false;
        }
        List<String> roleNames = userRolesList.stream().map(UserRoles::getRoleName).collect(Collectors.toList());
        List<RolePermissions> rolePermissionsList = rolePermissionsMapper.selectList(
                new LambdaQueryWrapper<RolePermissions>().in(RolePermissions::getRoleName, roleNames)
                        .eq(RolePermissions::isDeleted, false));
        if (CollectionUtils.isEmpty(rolePermissionsList)) {
            return false;
        }
        List<PermissionEnum> permissionsList = rolePermissionsList.stream().map(RolePermissions::getPermissionName).collect(Collectors.toList());
        redisUtil.set(redisKey, permissionsList);
        return permissionsList.contains(permission);

    }
}


