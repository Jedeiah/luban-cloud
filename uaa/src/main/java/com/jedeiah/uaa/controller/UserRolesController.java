package com.jedeiah.uaa.controller;

import com.jedeiah.uaa.entity.UserRoles;
import com.jedeiah.uaa.service.UserRolesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
@RestController
@RequestMapping("/user-roles")
@Tag(name = "用户角色关联表")
public class UserRolesController {

    @Autowired
    private UserRolesService userRolesService;


    @GetMapping("/selectOne")
    @Operation(summary = "UserRoles查询单个")
    public UserRoles getUserRoles(@RequestParam("id") Integer id) {
        UserRoles userRolesOne = userRolesService.getUserRoles(id);
        return userRolesOne;
    }

    @GetMapping("/listAll")
    @Operation(summary = "UserRoles查询全部")
    public List<UserRoles> getAllUserRoles() {
        List<UserRoles> userRolesList = userRolesService.getAllUserRoles();
        return userRolesList;
    }

    @PostMapping("/add")
    @Operation(summary = "UserRoles新增")
    public Object add(@Valid @RequestBody UserRoles userRoles) {
        userRolesService.add(userRoles);
        return null;
    }

    @PutMapping("/update")
    @Operation(summary = "UserRoles修改")
    public int update(@Valid @RequestBody UserRoles userRoles) {
        int num = userRolesService.modify(userRoles);
        return num;
    }


    @DeleteMapping(value = "/delete/{ids}")
    @Operation(summary = "UserRoles删除(单个条目)")
    public Object remove(@NotBlank(message = "{required}") @PathVariable String ids) {
        userRolesService.remove(ids);
        return null;
    }
}
