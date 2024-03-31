package com.jedeiah.uaa.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.jedeiah.commons.enums.PermissionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限关联表
 * </p>
 *
 * @author chj
 * @since 2024-03-30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("role_permissions")
@Schema(name = "RolePermissions", description = "角色权限关联表")
public class RolePermissions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色名称")
    @TableField("role_name")
    private String roleName;

    @Schema(description = "权限名称")
    @TableField("permission_name")
    private PermissionEnum permissionName;

    @Schema(description = "关联创建时间")
    @TableField("create_time")
    private Date createTime;

    @Schema(description = "逻辑删除标记")
    @TableField("deleted")
    @TableLogic
    private boolean deleted;
}
