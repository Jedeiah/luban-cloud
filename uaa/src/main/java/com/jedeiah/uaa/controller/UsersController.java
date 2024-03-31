package com.jedeiah.uaa.controller;

import com.jedeiah.commons.utls.JwtTokenUtil;
import com.jedeiah.commons.vo.RespVo;
import com.jedeiah.uaa.vo.UsersVo;

import com.jedeiah.commons.group.AddGroup;
import com.jedeiah.commons.group.UpdateGroup;
import com.jedeiah.uaa.entity.Users;
import com.jedeiah.uaa.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
@RestController
@RequestMapping("/users")
@Tag(name = "用户信息")
public class UsersController {

    @Autowired
    private UsersService usersService;


    @GetMapping(value = "/jwtToken")
    @Operation(summary = "获取token")
    public RespVo<String> getJwtToken(String usersId) {
        return RespVo.success(JwtTokenUtil.genAccessToken(usersId));
    }


    @GetMapping("/login/jwt")
    @Operation(summary = "jwt登陆")
    public RespVo loginJwt(@RequestParam("username") String username,@RequestParam("password") String password) {
        return usersService.loginJwt(username, password);
    }

    @GetMapping("/selectOne")
    @Operation(summary = "Users查询单个")
    public Users getUsers(@RequestParam("id") Integer id) {
        Users usersOne = usersService.getUsers(id);
        return usersOne;
    }

    @GetMapping("/listAll")
    @Operation(summary = "Users查询全部")
    public RespVo<List<Users>> getAllUsers() {
        List<Users> usersList = usersService.getAllUsers();
        return RespVo.success(usersList);
    }

    @PostMapping("/add")
    @Operation(summary = "Users新增，注册")
    public RespVo add(@Validated(AddGroup.class) @RequestBody UsersVo usersVo) {
        usersService.add(usersVo);
        return RespVo.success("注册成功！");
    }

    @PutMapping("/update")
    @Operation(summary = "Users修改")
    public int update(@Validated(UpdateGroup.class) @RequestBody Users users) {
        int num = usersService.modify(users);
        return num;
    }


    @DeleteMapping(value = "/delete/{ids}")
    @Operation(summary = "Users删除(单个条目)")
    public Object remove(@NotBlank(message = "请传id")
                         @PathVariable String ids) {
        usersService.remove(ids);
        return null;
    }
}
