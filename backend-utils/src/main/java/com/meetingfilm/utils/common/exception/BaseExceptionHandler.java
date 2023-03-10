package com.meetingfilm.utils.common.exception;

import com.meetingfilm.utils.common.vo.BaseResponseVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

//公共异常处理
@Slf4j
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(CommonServiceException.class)
    @ResponseBody
    public BaseResponseVO serviceExceptionHandler(HttpServletRequest request, CommonServiceException e){

        log.error("CommonServiceException, code:{}, message", e.getCode(),e.getMessage());

        return  BaseResponseVO.serviceException(e);

    }
}
