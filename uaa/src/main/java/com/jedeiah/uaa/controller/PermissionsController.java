package com.jedeiah.uaa.controller;

import com.jedeiah.uaa.entity.Permissions;
import com.jedeiah.uaa.service.PermissionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
@RestController
@RequestMapping("/permissions")
@Tag(name = "权限信息表")
public class PermissionsController {

    @Autowired
    private PermissionsService permissionsService;


    @GetMapping("/selectOne")
    @Operation(summary = "Permissions查询单个")
    public Permissions getPermissions(@RequestParam("id") Integer id) {
        Permissions permissionsOne = permissionsService.getPermissions(id);
        return permissionsOne;
    }

    @GetMapping("/listAll")
    @Operation(summary = "Permissions查询全部")
    public List<Permissions> getAllPermissions() {
        List<Permissions> permissionsList = permissionsService.getAllPermissions();
        return permissionsList;
    }

    @PostMapping("/add")
    @Operation(summary = "Permissions新增")
    public Object add(@Valid @RequestBody Permissions permissions) {
        permissionsService.add(permissions);
        return null;
    }

    @PutMapping("/update")
    @Operation(summary = "Permissions修改")
    public int update(@Valid @RequestBody Permissions permissions) {
        int num = permissionsService.modify(permissions);
        return num;
    }


    @DeleteMapping(value = "/delete/{ids}")
    @Operation(summary = "Permissions删除(单个条目)")
    public Object remove(@NotBlank(message = "{required}") @PathVariable String ids) {
        permissionsService.remove(ids);
        return null;
    }
}
