package com.jedeiah.uaa.controller;

import com.jedeiah.uaa.commons.RespVo;
import com.jedeiah.uaa.entity.Users;
import com.jedeiah.uaa.service.UsersService;
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
@RequestMapping("/users")
@Tag(name = "用户信息表")
public class UsersController {

    @Autowired
    private UsersService usersService;


    /***
     * 用户登录
     */
    @RequestMapping(value = "/login")
    public RespVo login(String username, String password) {
        return usersService.login(username, password);
    }


    @GetMapping("/selectOne")
    @Operation(summary = "Users查询单个")
    public Users getUsers(@RequestParam("id") Integer id) {
        Users usersOne = usersService.getUsers(id);
        return usersOne;
    }

    @GetMapping("/listAll")
    @Operation(summary = "Users查询全部")
    public List<Users> getAllUsers() {
        List<Users> usersList = usersService.getAllUsers();
        return usersList;
    }

    @PostMapping("/add")
    @Operation(summary = "Users新增")
    public Object add(@Valid @RequestBody Users users) {
        usersService.add(users);
        return null;
    }

    @PutMapping("/update")
    @Operation(summary = "Users修改")
    public int update(@Valid @RequestBody Users users) {
        int num = usersService.modify(users);
        return num;
    }


    @DeleteMapping(value = "/delete/{ids}")
    @Operation(summary = "Users删除(单个条目)")
    public Object remove(@NotBlank(message = "{required}") @PathVariable String ids) {
        usersService.remove(ids);
        return null;
    }
}
