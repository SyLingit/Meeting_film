package com.meetingfilm.cinema.controller.VO;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.vo.BaseRequestVO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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

    public static CinemaSavedReqVO pojo2VO(CinemaDescribeRespVO entity) {

        CinemaSavedReqVO cinemaSavedReqVO = new CinemaSavedReqVO();
        BeanUtils.copyProperties(entity,cinemaSavedReqVO);
        return cinemaSavedReqVO;

    }

    @Override
    public void checkParam()throws CommonServiceException{


    }

}
