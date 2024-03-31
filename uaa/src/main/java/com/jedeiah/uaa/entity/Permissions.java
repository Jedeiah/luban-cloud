package com.jedeiah.uaa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.jedeiah.commons.enums.PermissionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 权限信息表
 * </p>
 *
 * @author chj
 * @since 2024-03-30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("permissions")
@Schema(name = "Permissions", description = "权限信息表")
public class Permissions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "权限名称")
    @TableField("permission_name")
    private PermissionEnum permissionName;

    @Schema(description = "权限描述")
    @TableField("description")
    private String description;

    @Schema(description = "逻辑删除标记")
    @TableField("deleted")
    @TableLogic
    private boolean deleted;
}
