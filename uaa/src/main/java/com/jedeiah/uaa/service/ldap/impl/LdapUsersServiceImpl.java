package com.jedeiah.uaa.service.ldap.impl;

import com.jedeiah.commons.enums.LoginTypeEnum;
import com.jedeiah.commons.enums.PermissionEnum;
import com.jedeiah.commons.utls.JwtTokenUtil;
import com.jedeiah.commons.vo.RespVo;
import com.jedeiah.uaa.entity.ldap.Permission;
import com.jedeiah.uaa.entity.ldap.Role;
import com.jedeiah.uaa.entity.ldap.User;
import com.jedeiah.uaa.mapper.ldap.PermissionRepository;
import com.jedeiah.uaa.mapper.ldap.RoleRepository;
import com.jedeiah.uaa.mapper.ldap.UserRepository;
import com.jedeiah.uaa.service.ldap.LdapUsersService;
import com.jedeiah.uaa.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.AuthenticationException;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.LdapQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LdapUsersServiceImpl implements LdapUsersService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private LdapTemplate ldapTemplate;
    @Autowired
    private RedisUtil redisUtil;

    @Value(",${spring.ldap.base:#{null}}")
    private String ldapBase;


    @Override
    public RespVo login(String username, String password) {
        User user = userRepository.findByUsername(username);
        Assert.notNull(user, "账号或者密码错误");
        LdapQuery query = LdapQueryBuilder.query()
                .base(user.getId())
                .where("uid").is(user.getUid());
        try {
            ldapTemplate.authenticate(query, password);
            // 密码校验通过
        } catch (AuthenticationException e) {
            // 密码校验失败
            throw new RuntimeException("密码错误");
        }
        //生成令牌
        String jwtToken = JwtTokenUtil.genAccessToken(user.getUid(), LoginTypeEnum.LDAP);
        return RespVo.success(jwtToken);
    }

    @Override
    public boolean hasPermission(String userId, PermissionEnum permission) {
        String redisKey = "permission:ldmp:" + userId;
        List<PermissionEnum> values = (List<PermissionEnum>) redisUtil.get(redisKey);
        if (values != null) {
            return values.contains(permission);
        }

        User user = userRepository.findByUid(userId);
        if (user == null) {
            return false;
        }
        List<Role> roles = roleRepository.findRolesBymemberOn(user.getId() + ldapBase);
        if (CollectionUtils.isEmpty(roles)) {
            return false;
        }

        List<PermissionEnum> permissionsList = roles.stream()
                .flatMap(role -> permissionRepository.findPermissionsBymemberOn(role.getId() + ldapBase).stream())
                .map(Permission::getPermissionName)
                .collect(Collectors.toSet())
                .stream()
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(permissionsList)) {
            return false;
        }
        redisUtil.set(redisKey, permissionsList, 60 * 60 * 24);
        return permissionsList.contains(permission);
    }


}
