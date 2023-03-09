package com.meetingfilm.utils.common.exception;

import lombok.Data;

//公共的业务逻辑错误
@Data
public class CommonServiceException extends Throwable {

    private Integer code;
    private String message;

    public CommonServiceException(int code, String message){
        this.code = code;
        this.message = message;
    }

}
