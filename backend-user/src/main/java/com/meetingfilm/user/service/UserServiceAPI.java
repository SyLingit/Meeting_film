package com.meetingfilm.user.service;

import com.meetingfilm.utils.common.exception.CommonServiceException;

//用户接口
public interface UserServiceAPI {

    String checkUserLogin(String username, String password) throws CommonServiceException;
}
