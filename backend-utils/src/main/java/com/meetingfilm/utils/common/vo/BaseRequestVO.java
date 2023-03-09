package com.meetingfilm.utils.common.vo;

//公共的请求对象

import com.meetingfilm.utils.common.exception.CommonServiceException;

public abstract class BaseRequestVO {
    //公共的参数验证方法
    public abstract void checkParam() throws CommonServiceException;
}
