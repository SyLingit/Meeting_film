package com.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meetingfilm.cinema.controller.VO.CinemaSavedReqVO;
import com.meetingfilm.cinema.controller.VO.DescribeCinemasRespVO;
import com.meetingfilm.utils.common.exception.CommonServiceException;

public interface CinemaServiceAPI {

    //保存影院
    void  saveCinema(CinemaSavedReqVO reqVO)throws CommonServiceException;

    //获取影院列表
    IPage<DescribeCinemasRespVO> describeCinemas(int nowPage,int pageSize)throws CommonServiceException;

}
