package com.jedeiah.uaa.service.impl;

import com.jedeiah.uaa.entity.UserRoles;
import com.jedeiah.uaa.mapper.UserRolesMapper;
import com.jedeiah.uaa.service.UserRolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Arrays;

/**
 * 用户角色关联表 服务实现类
 *
 * @author chj
 * @since 2024-03-30
 */
@Service
public class UserRolesServiceImpl extends ServiceImpl<UserRolesMapper, UserRoles> implements UserRolesService {

    @Autowired
    UserRolesMapper userRolesMapper;


    @Override
    public UserRoles getUserRoles(Integer id) {

        return userRolesMapper.selectById(id);
    }

    @Override
    public List<UserRoles> getAllUserRoles() {
        return userRolesMapper.selectList(null);

    }

    @Override
    public void add(UserRoles userRoles) {
        userRolesMapper.insert(userRoles);
    }

    @Override
    public int modify(UserRoles userRoles) {
        return userRolesMapper.updateById(userRoles);
    }

    @Override
    public void remove(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            String[] array = ids.split(",");
            if (!CollectionUtils.isEmpty(Arrays.asList(array))) {
                userRolesMapper.deleteBatchIds(Arrays.asList(array));
            }
        }

    }

}


