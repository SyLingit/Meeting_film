package com.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meetingfilm.cinema.controller.VO.CinemaDescribeRespVO;
import com.meetingfilm.cinema.controller.VO.CinemaSavedReqVO;
import com.meetingfilm.utils.common.exception.CommonServiceException;

public interface CinemaServiceAPI {

    //保存影院的方法
    void saveCinema(CinemaSavedReqVO reqVO)throws CommonServiceException;

    //获取影院列表
    IPage<CinemaDescribeRespVO> cinemasDescribe(int nowPage, int pageSize);

}
