package com.jedeiah.uaa.service;

import com.jedeiah.uaa.entity.RolePermissions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
public interface RolePermissionsService extends IService<RolePermissions> {

    /**
     * RolePermissions详情
     *
     * @param
     * @return
     */
    RolePermissions getRolePermissions(Integer id);

    /**
     * RolePermissions详情
     *
     * @param
     * @return
     */
    List<RolePermissions> getAllRolePermissions();

    /**
     * RolePermissions新增
     *
     * @param rolePermissions 根据需要进行传值
     * @return
     */
    void add(RolePermissions rolePermissions);

    /**
     * RolePermissions修改
     *
     * @param rolePermissions 根据需要进行传值
     * @return
     */
    int modify(RolePermissions rolePermissions);

    /**
     * RolePermissions删除
     *
     * @param ids
     * @return
     */
    void remove(String ids);
}


