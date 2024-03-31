package com.jedeiah.uaa.vo;

import com.jedeiah.commons.group.AddGroup;
import com.jedeiah.commons.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Schema(name = "UsersVo", description = "用户信息")
public class UsersVo {

    @Schema(description = "用户唯一标识uuid")
    private String userId;

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String username;

    @Schema(description = "密码")
    @NotBlank(message = "密码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String password;

    @Schema(description = "账户创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Schema(description = "信息更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

}
