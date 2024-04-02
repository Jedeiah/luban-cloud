package com.jedeiah.uaa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jedeiah.commons.utls.JwtTokenUtil;
import com.jedeiah.commons.vo.RespVo;
import com.jedeiah.uaa.entity.UserRoles;
import com.jedeiah.uaa.entity.Users;
import com.jedeiah.uaa.mapper.UserRolesMapper;
import com.jedeiah.uaa.mapper.UsersMapper;
import com.jedeiah.uaa.service.UsersService;
import com.jedeiah.uaa.vo.GitHubUser;
import com.jedeiah.uaa.vo.UsersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 用户信息表 服务实现类
 *
 * @author chj
 * @since 2024-03-30
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    UserRolesMapper userRolesMapper;


    @Override
    public Users getUsers(Integer id) {

        return usersMapper.selectById(id);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersMapper.selectList(null);

    }

    @Override
    @Transactional
    public void add(UsersVo usersVo) {
        String username = usersVo.getUsername();
        boolean exists = this.exists(new LambdaQueryWrapper<Users>().eq(Users::getUsername, username).eq(Users::isDeleted, false));
        Assert.isTrue(!exists, "用户名已存在");

        Users users = new Users();
        users.setUsername(username);
        users.setPassword(usersVo.getPassword()); //todo 密码加密

        //应该手动绑定， 或者一定的规则，我为了省事
        UserRoles.UserRolesBuilder userRolesBuilder = UserRoles.builder().userId(users.getUserId()).roleName("USER");
        if (username.matches(".*editor.*")) {
            userRolesBuilder.roleName("EDITOR");
        } else if (username.matches(".*adm.*")) {
            userRolesBuilder.roleName("PRODUCT_ADMIN");
        }
        usersMapper.insert(users);
        userRolesMapper.insert(userRolesBuilder.build());
    }

    @Override
    public int modify(Users users) {
        
        Users currentUsers = usersMapper.selectById(users.getId());
        return usersMapper.updateById(users);
    }

    @Override
    public void remove(String ids) {

        if (StringUtils.hasLength(ids)) {
            String[] array = ids.split(",");
            if (!CollectionUtils.isEmpty(Arrays.asList(array))) {
                usersMapper.deleteBatchIds(Arrays.asList(array));
            }
        }

    }

    @Override
    public RespVo loginJwt(String username, String password) {
        //去数据库查询用户信息（用户名是主键）
        Users user = this.getOne(new LambdaQueryWrapper<Users>().eq(Users::getUsername, username).eq(Users::isDeleted, false).last(" limit 1"));
        Assert.notNull(user, "账号或者密码错误");
        // todo 密码验证
//        boolean checkpw = BCrypt.checkpw(password, user.getPassword());
        boolean checkpw = user.getPassword().equals(password);
        Assert.isTrue(checkpw, "账号或者密码错误");
        //生成令牌
        String jwtToken = JwtTokenUtil.genAccessToken(user.getUserId());
        return RespVo.success(jwtToken);
    }

    @Override
    @Transactional
    public String loginOrRegister(GitHubUser userInfo) {
        String username = userInfo.getLogin();
        String userId = "github_" + userInfo.getId();
        Assert.isTrue(StringUtils.hasLength(username), "授权异常，请稍后重试");
        Users user = this.getOne(new LambdaQueryWrapper<Users>().eq(Users::getUsername, username).eq(Users::isDeleted, false).last(" limit 1"));
        if (user != null) {
            return JwtTokenUtil.genAccessToken(user.getUserId());
        }
        Users users = new Users();
        users.setUserId(userId);
        users.setUsername(username);
        users.setPassword(UUID.randomUUID().toString());// todo 授权的用户密码设置
        //授权用户指定角色
        UserRoles.UserRolesBuilder userRolesBuilder = UserRoles.builder().userId(users.getUserId()).roleName("EDITOR");
        usersMapper.insert(users);
        userRolesMapper.insert(userRolesBuilder.build());
        return JwtTokenUtil.genAccessToken(userId);
    }

}


