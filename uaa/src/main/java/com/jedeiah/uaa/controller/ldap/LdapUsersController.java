package com.jedeiah.uaa.controller.ldap;

import com.jedeiah.commons.vo.RespVo;
import com.jedeiah.uaa.service.ldap.LdapUsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chj
 * @since 2024-03-30
 */
@RestController
@RequestMapping("/ldap")
@Tag(name = "lda用户信息")
public class LdapUsersController {


    @Autowired
    private LdapUsersService ldapUsersService;

    @GetMapping("/login")
    @Operation(summary = "jwt登陆")
    public RespVo login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return ldapUsersService.login(username, password);
    }


}
