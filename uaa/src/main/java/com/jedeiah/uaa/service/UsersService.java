package com.jedeiah.uaa.service;

import com.jedeiah.uaa.vo.UsersVo;
import com.jedeiah.commons.vo.RespVo;
import com.jedeiah.uaa.entity.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author chj
 * @since 2024-03-30
 */
public interface UsersService extends IService<Users> {

    /**
     * Users详情
     *
     * @param
     * @return
     */
    Users getUsers(Integer id);

    /**
     * Users详情
     *
     * @param
     * @return
     */
    List<Users> getAllUsers();

    /**
     * Users新增
     *
     * @param usersVo 根据需要进行传值
     * @return
     */
    void add(UsersVo usersVo);

    /**
     * Users修改
     *
     * @param users 根据需要进行传值
     * @return
     */
    int modify(Users users);

    /**
     * Users删除
     *
     * @param ids
     * @return
     */
    void remove(String ids);

    RespVo loginJwt(String username, String password);
}


