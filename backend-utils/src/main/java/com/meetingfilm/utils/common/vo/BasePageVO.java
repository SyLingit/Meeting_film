package com.meetingfilm.utils.common.vo;

//分页请求类

import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.util.ToolUtils;
import lombok.Data;

@Data
public class BasePageVO extends BaseRequestVO {

    private Integer nowPage = 1;
    private Integer pageSize = 10;

    @Override
    public void checkParam() throws CommonServiceException {

        if(ToolUtils.strIsNull(String.valueOf(nowPage)) || ToolUtils.strIsNull(String.valueOf(pageSize))) {
            throw new CommonServiceException(404, "nowPage and pageSize must be not empty!");
        }
    }
}
