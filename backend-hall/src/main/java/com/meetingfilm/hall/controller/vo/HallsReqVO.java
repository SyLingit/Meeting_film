package com.meetingfilm.hall.controller.vo;

import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.vo.BasePageVO;
import lombok.Data;

@Data
public class HallsReqVO extends BasePageVO {

    private String cinemaId;

    @Override
    public void checkParam() throws CommonServiceException {
        super.checkParam();
    }
}
