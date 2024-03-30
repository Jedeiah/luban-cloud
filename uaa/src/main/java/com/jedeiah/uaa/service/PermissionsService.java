package com.jedeiah.uaa.service;

import com.jedeiah.uaa.entity.Permissions;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
public interface PermissionsService extends IService<Permissions> {

    /**
     * Permissions详情
     *
     * @param
     * @return
     */
    Permissions getPermissions(Integer id);

    /**
     * Permissions详情
     *
     * @param
     * @return
     */
    List<Permissions> getAllPermissions();

    /**
     * Permissions新增
     *
     * @param permissions 根据需要进行传值
     * @return
     */
    void add(Permissions permissions);

    /**
     * Permissions修改
     *
     * @param permissions 根据需要进行传值
     * @return
     */
    int modify(Permissions permissions);

    /**
     * Permissions删除
     *
     * @param ids
     * @return
     */
    void remove(String ids);
}


