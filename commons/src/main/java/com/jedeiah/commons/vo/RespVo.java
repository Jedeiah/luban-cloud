package com.jedeiah.commons.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "响应视图对象")
public class RespVo<T> {

    @Schema(description = "请求处理是否成功")
    private boolean success;

    @Schema(description = "错误编码")
    private String errCode;

    @Schema(description = "错误消息")
    private String errMsg;

    @Schema(description = "响应内容实体")
    private T data;

    // 成功和失败的静态工厂方法
    public static <T> RespVo<T> success() {
        return new RespVo<>(true, null, null, null);
    }

    public static <T> RespVo<T> success(T data) {
        return new RespVo<>(true, null, null, data);
    }

    public static RespVo error(String errCode, String errMsg) {
        return new RespVo<>(false, errCode, errMsg, null);
    }
}