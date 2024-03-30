package com.jedeiah.uaa.service.impl;

import com.jedeiah.uaa.entity.Roles;
import com.jedeiah.uaa.mapper.RolesMapper;
import com.jedeiah.uaa.service.RolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Arrays;

/**
 * 角色信息表 服务实现类
 *
 * @author chj
 * @since 2024-03-30
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements RolesService {

    @Autowired
    RolesMapper rolesMapper;


    @Override
    public Roles getRoles(Integer id) {

        return rolesMapper.selectById(id);
    }

    @Override
    public List<Roles> getAllRoles() {
        return rolesMapper.selectList(null);

    }

    @Override
    public void add(Roles roles) {
        rolesMapper.insert(roles);
    }

    @Override
    public int modify(Roles roles) {
        //乐观锁更新
        Roles currentRoles = rolesMapper.selectById(roles.getId());
        return rolesMapper.updateById(roles);
    }

    @Override
    public void remove(String ids) {

        if (StringUtils.isNotEmpty(ids)) {
            String[] array = ids.split(",");
            if (!CollectionUtils.isEmpty(Arrays.asList(array))) {
                rolesMapper.deleteBatchIds(Arrays.asList(array));
            }
        }

    }

}


