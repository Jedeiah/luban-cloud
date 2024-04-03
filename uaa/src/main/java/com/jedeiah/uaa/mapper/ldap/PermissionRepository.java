package com.jedeiah.uaa.mapper.ldap;

import com.jedeiah.uaa.entity.ldap.Permission;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.ldap.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends LdapRepository<Permission> {
    /**
     * 根据角色DN查找角色所拥有的权限
     * @param memberOn
     * @return
     */
    @Query("(&(objectClass=groupOfNames)(member={0}))")
    List<Permission> findPermissionsBymemberOn(@Param("memberOn") String memberOn);
}