package com.jedeiah.uaa.mapper;

import com.jedeiah.uaa.entity.RolePermissions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 角色权限关联表 Mapper 接口
 * </p>
 *
 * @author chj
 * @since 2024-03-30
 */
@Mapper
public interface RolePermissionsMapper extends BaseMapper<RolePermissions> {

}
