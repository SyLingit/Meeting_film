package com.meetingfilm.user.controller.vo;

import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.util.ToolUtils;
import com.meetingfilm.utils.common.vo.BaseRequestVO;
import lombok.Data;

//登陆请求对象
@Data
public class LoginReqVO extends BaseRequestVO {

    private String username;
    private String password;

    @Override
    public void checkParam() throws CommonServiceException {
        //验证数据合法性
        if(ToolUtils.strIsNull(username) || ToolUtils.strIsNull(password)){
            throw new CommonServiceException(404,"username or password must be required!");
        }
    }

}
