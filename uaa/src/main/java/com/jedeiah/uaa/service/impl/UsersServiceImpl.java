package com.jedeiah.uaa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jedeiah.commons.utls.JwtTokenUtil;
import com.jedeiah.uaa.vo.UsersVo;
import com.jedeiah.commons.vo.RespVo;
import com.jedeiah.uaa.entity.UserRoles;
import com.jedeiah.uaa.entity.Users;
import com.jedeiah.uaa.mapper.UserRolesMapper;
import com.jedeiah.uaa.mapper.UsersMapper;
import com.jedeiah.uaa.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;

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
        boolean exists = this.exists(new LambdaQueryWrapper<Users>().eq(Users::getUsername, usersVo.getUsername()).eq(Users::isDeleted, false));
        Assert.isTrue(!exists, "用户名已存在");
        //todo 密码加密
        Users users = new Users();
        users.setUsername(usersVo.getUsername());
        users.setPassword(usersVo.getPassword());
        String username = usersVo.getUsername();
        //应该手动绑定， 或者一定的规则，我为了省事
        UserRoles.UserRolesBuilder userRolesBuilder = UserRoles.builder().userId(users.getUserId()).roleName("USER");
        if (username.matches(".*\\beditor\\b.*")) {
            userRolesBuilder.roleName("EDITOR");
        } else if (username.matches(".*\\badm\\b.*")) {
            userRolesBuilder.roleName("PRODUCT_ADMIN");
        }
        usersMapper.insert(users);
        userRolesMapper.insert(userRolesBuilder.build());
    }

    @Override
    public int modify(Users users) {
        //乐观锁更新
        Users currentUsers = usersMapper.selectById(users.getId());
        return usersMapper.updateById(users);
    }

    @Override
    public void remove(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            String[] array = ids.split(",");
            if (!CollectionUtils.isEmpty(Arrays.asList(array))) {
                usersMapper.deleteBatchIds(Arrays.asList(array));
            }
        }

    }

    @Override
    public RespVo loginJwt(String username, String password) {
        //去数据库查询用户信息（用户名是主键）
        Users user = this.getOne(new LambdaQueryWrapper<Users>().eq(Users::getUsername, username).eq(Users::isDeleted, false));
        Assert.notNull(user, "账号或者密码错误");
        // todo 密码验证
//        boolean checkpw = BCrypt.checkpw(password, user.getPassword());
        boolean checkpw = user.getPassword().equals(password);
        Assert.isTrue(checkpw, "账号或者密码错误");
        //生成令牌
        String jwtToken = JwtTokenUtil.genAccessToken(user.getUserId());
        return RespVo.success(jwtToken);
    }

}


