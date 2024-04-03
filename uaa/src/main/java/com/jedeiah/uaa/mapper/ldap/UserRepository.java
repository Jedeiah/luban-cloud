package com.jedeiah.uaa.mapper.ldap;

import com.jedeiah.uaa.entity.ldap.User;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.ldap.repository.Query;

public interface UserRepository extends LdapRepository<User> {

    @Query("(&(objectClass=inetOrgPerson)(cn={0}))")
    User findByUsername(String cn);

    @Query("(&(objectClass=inetOrgPerson)(uid={0}))")
    User findByUid(String userId);
}