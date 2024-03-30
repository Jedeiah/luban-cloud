package com.jedeiah.uaa.controller;

import com.jedeiah.uaa.entity.Roles;
import com.jedeiah.uaa.service.RolesService;
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
@RequestMapping("/roles")
@Tag(name = "角色信息表")
public class RolesController {

    @Autowired
    private RolesService rolesService;


    @GetMapping("/selectOne")
    @Operation(summary = "Roles查询单个")
    public Roles getRoles(@RequestParam("id") Integer id) {
        Roles rolesOne = rolesService.getRoles(id);
        return rolesOne;
    }

    @GetMapping("/listAll")
    @Operation(summary = "Roles查询全部")
    public List<Roles> getAllRoles() {
        List<Roles> rolesList = rolesService.getAllRoles();
        return rolesList;
    }

    @PostMapping("/add")
    @Operation(summary = "Roles新增")
    public Object add(@Valid @RequestBody Roles roles) {
        rolesService.add(roles);
        return null;
    }

    @PutMapping("/update")
    @Operation(summary = "Roles修改")
    public int update(@Valid @RequestBody Roles roles) {
        int num = rolesService.modify(roles);
        return num;
    }


    @DeleteMapping(value = "/delete/{ids}")
    @Operation(summary = "Roles删除(单个条目)")
    public Object remove(@NotBlank(message = "{required}") @PathVariable String ids) {
        rolesService.remove(ids);
        return null;
    }
}
