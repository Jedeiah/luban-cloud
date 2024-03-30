package com.jedeiah.uaa.service;

import com.jedeiah.uaa.entity.UserRoles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
public interface UserRolesService extends IService<UserRoles> {

    /**
     * UserRoles详情
     *
     * @param
     * @return
     */
    UserRoles getUserRoles(Integer id);

    /**
     * UserRoles详情
     *
     * @param
     * @return
     */
    List<UserRoles> getAllUserRoles();

    /**
     * UserRoles新增
     *
     * @param userRoles 根据需要进行传值
     * @return
     */
    void add(UserRoles userRoles);

    /**
     * UserRoles修改
     *
     * @param userRoles 根据需要进行传值
     * @return
     */
    int modify(UserRoles userRoles);

    /**
     * UserRoles删除
     *
     * @param ids
     * @return
     */
    void remove(String ids);
}


