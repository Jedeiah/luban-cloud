package com.jedeiah.uaa.mapper;

import com.jedeiah.uaa.entity.UserRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author chj
 * @since 2024-03-30
 */
@Mapper
public interface UserRolesMapper extends BaseMapper<UserRoles> {

}
