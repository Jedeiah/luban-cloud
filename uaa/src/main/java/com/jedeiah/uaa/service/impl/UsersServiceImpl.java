package com.jedeiah.uaa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jedeiah.uaa.commons.RespVo;
import com.jedeiah.uaa.entity.Users;
import com.jedeiah.uaa.mapper.UsersMapper;
import com.jedeiah.uaa.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
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


    @Override
    public Users getUsers(Integer id) {

        return usersMapper.selectById(id);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersMapper.selectList(null);

    }

    @Override
    public void add(Users users) {
        usersMapper.insert(users);
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
    public RespVo login(String username, String password) {
        //去数据库查询用户信息（用户名是主键）
        Users user = this.getOne(new LambdaQueryWrapper<Users>().eq(Users::getUsername, username));
        Assert.notNull(user, "账号或者密码错误");
        boolean checkpw = BCrypt.checkpw(password, user.getPassword());
        Assert.isTrue(!checkpw, "账号或者密码错误");

        //设置令牌信息
        Map<String, Object> info = CollectionUtils.newHashMap(3);
        info.put("role", "USER");
        info.put("success", "SUCCESS");
        info.put("username", username);
        //生成令牌

        return RespVo.success();// todo
    }

}


