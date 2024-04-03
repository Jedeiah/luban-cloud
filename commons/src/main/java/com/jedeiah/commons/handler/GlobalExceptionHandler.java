package com.jedeiah.commons.handler;

import com.jedeiah.commons.vo.RespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespVo handlEexception(Exception e) {
        if (log.isDebugEnabled()) {
            log.debug("exception occurred", e);
        }
        return RespVo.error(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),e.getMessage());
    }
}