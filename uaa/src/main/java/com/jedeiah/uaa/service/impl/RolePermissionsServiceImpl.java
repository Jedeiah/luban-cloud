package com.jedeiah.uaa.service.impl;

import com.jedeiah.uaa.entity.RolePermissions;
import com.jedeiah.uaa.mapper.RolePermissionsMapper;
import com.jedeiah.uaa.service.RolePermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Arrays;

/**
 * 角色权限关联表 服务实现类
 *
 * @author chj
 * @since 2024-03-30
 */
@Service
public class RolePermissionsServiceImpl extends ServiceImpl<RolePermissionsMapper, RolePermissions> implements RolePermissionsService {

    @Autowired
    RolePermissionsMapper rolePermissionsMapper;


    @Override
    public RolePermissions getRolePermissions(Integer id) {

        return rolePermissionsMapper.selectById(id);
    }

    @Override
    public List<RolePermissions> getAllRolePermissions() {
        return rolePermissionsMapper.selectList(null);

    }

    @Override
    public void add(RolePermissions rolePermissions) {
        rolePermissionsMapper.insert(rolePermissions);
    }

    @Override
    public int modify(RolePermissions rolePermissions) {
        return rolePermissionsMapper.updateById(rolePermissions);
    }

    @Override
    public void remove(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            String[] array = ids.split(",");
            if (!CollectionUtils.isEmpty(Arrays.asList(array))) {
                rolePermissionsMapper.deleteBatchIds(Arrays.asList(array));
            }
        }

    }

}


