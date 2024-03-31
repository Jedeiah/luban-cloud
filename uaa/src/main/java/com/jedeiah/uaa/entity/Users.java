package com.jedeiah.uaa.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息表
 * </p>
 *
 * @author chj
 * @since 2024-03-30
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("users")
@Schema(name = "Users", description = "用户信息表")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户唯一标识uuid")
    @TableField("user_id")
    private String userId = UUID.randomUUID().toString();

    @Schema(description = "用户名")
    @TableField("username")
    private String username;

    @Schema(description = "加密后的密码")
    @TableField("password")
    private String password;

    @Schema(description = "账户创建时间")
    @TableField("create_time")
    private Date createTime;

    @Schema(description = "信息更新时间")
    @TableField("update_time")
    private Date updateTime;

    @Schema(description = "逻辑删除标记")
    @TableField("deleted")
    @TableLogic
    private boolean deleted;
}
