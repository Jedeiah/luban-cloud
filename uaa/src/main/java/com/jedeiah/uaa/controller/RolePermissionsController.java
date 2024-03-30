package com.jedeiah.uaa.controller;

import com.jedeiah.uaa.entity.RolePermissions;
import com.jedeiah.uaa.service.RolePermissionsService;
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
@RequestMapping("/role-permissions")
@Tag(name = "角色权限关联表")
public class RolePermissionsController {

    @Autowired
    private RolePermissionsService rolePermissionsService;


    @GetMapping("/selectOne")
    @Operation(summary = "RolePermissions查询单个")
    public RolePermissions getRolePermissions(@RequestParam("id") Integer id) {
        RolePermissions rolePermissionsOne = rolePermissionsService.getRolePermissions(id);
        return rolePermissionsOne;
    }

    @GetMapping("/listAll")
    @Operation(summary = "RolePermissions查询全部")
    public List<RolePermissions> getAllRolePermissions() {
        List<RolePermissions> rolePermissionsList = rolePermissionsService.getAllRolePermissions();
        return rolePermissionsList;
    }

    @PostMapping("/add")
    @Operation(summary = "RolePermissions新增")
    public Object add(@Valid @RequestBody RolePermissions rolePermissions) {
        rolePermissionsService.add(rolePermissions);
        return null;
    }

    @PutMapping("/update")
    @Operation(summary = "RolePermissions修改")
    public int update(@Valid @RequestBody RolePermissions rolePermissions) {
        int num = rolePermissionsService.modify(rolePermissions);
        return num;
    }


    @DeleteMapping(value = "/delete/{ids}")
    @Operation(summary = "RolePermissions删除(单个条目)")
    public Object remove(@NotBlank(message = "{required}") @PathVariable String ids) {
        rolePermissionsService.remove(ids);
        return null;
    }
}
