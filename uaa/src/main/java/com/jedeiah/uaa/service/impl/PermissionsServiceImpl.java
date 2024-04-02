package com.jedeiah.uaa.service.impl;

import com.jedeiah.uaa.entity.Permissions;
import com.jedeiah.uaa.mapper.PermissionsMapper;
import com.jedeiah.uaa.service.PermissionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Arrays;

/**
 * 权限信息表 服务实现类
 *
 * @author chj
 * @since 2024-03-30
 */
@Service
public class PermissionsServiceImpl extends ServiceImpl<PermissionsMapper, Permissions> implements PermissionsService {

    @Autowired
    PermissionsMapper permissionsMapper;


    @Override
    public Permissions getPermissions(Integer id) {

        return permissionsMapper.selectById(id);
    }

    @Override
    public List<Permissions> getAllPermissions() {
        return permissionsMapper.selectList(null);

    }

    @Override
    public void add(Permissions permissions) {
        permissionsMapper.insert(permissions);
    }

    @Override
    public int modify(Permissions permissions) {
        
        Permissions currentPermissions = permissionsMapper.selectById(permissions.getId());
        return permissionsMapper.updateById(permissions);
    }

    @Override
    public void remove(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            String[] array = ids.split(",");
            if (!CollectionUtils.isEmpty(Arrays.asList(array))) {
                permissionsMapper.deleteBatchIds(Arrays.asList(array));
            }
        }

    }

}


