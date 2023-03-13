package com.meetingfilm.cinema.controller.VO;

import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.vo.BaseRequestVO;
import lombok.Data;


@Data
public class CinemaSavedReqVO extends BaseRequestVO {

    private String brandId;
    private String areaId;
    private String hallTypeIds;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaTele;
    private String cinemaImgAddress;
    private String cinemaPrice;


    @Override
    public void checkParam() throws CommonServiceException {
        //TODO 参数校验
    }
}
