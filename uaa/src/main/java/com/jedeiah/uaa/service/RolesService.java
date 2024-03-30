package com.jedeiah.uaa.service;

import com.jedeiah.uaa.entity.Roles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
public interface RolesService extends IService<Roles> {

    /**
     * Roles详情
     *
     * @param
     * @return
     */
    Roles getRoles(Integer id);

    /**
     * Roles详情
     *
     * @param
     * @return
     */
    List<Roles> getAllRoles();

    /**
     * Roles新增
     *
     * @param roles 根据需要进行传值
     * @return
     */
    void add(Roles roles);

    /**
     * Roles修改
     *
     * @param roles 根据需要进行传值
     * @return
     */
    int modify(Roles roles);

    /**
     * Roles删除
     *
     * @param ids
     * @return
     */
    void remove(String ids);
}


