package com.meetingfilm.utils.common.vo;

import com.meetingfilm.utils.common.exception.CommonServiceException;
import lombok.Data;

@Data
public class BaseResponseVO<M> {

    private Integer code;      //业务编号
    private String message;    //异常信息
    private M data;            //业务数据返回

    private BaseResponseVO(){}

    // 成功但是无参数
    public static BaseResponseVO success(){
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");

        return response;
    }

    //成功有参数
    public static<M> BaseResponseVO success(M data){
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(200);
        response.setMessage("");
        response.setData(data);

        return response;
    }

    //出现业务异常
    public static<M> BaseResponseVO serviceException(CommonServiceException e){
        BaseResponseVO response = new BaseResponseVO();
        response.setCode(e.getCode());
        response.setMessage(e.getMessage());

        return response;
    }
}
