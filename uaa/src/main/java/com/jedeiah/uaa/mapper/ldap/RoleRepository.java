package com.jedeiah.uaa.mapper.ldap;

import com.jedeiah.uaa.entity.ldap.Role;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.ldap.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.naming.Name;
import java.util.List;

public interface RoleRepository extends LdapRepository<Role> {

    /**
     * 根据用户DN查找角色所拥有的角色
     * @param memberOn
     * @return
     */
    @Query("(&(objectClass=groupOfNames)(member={0}))")
    List<Role> findRolesBymemberOn(@Param("memberOn") String memberOn);
}