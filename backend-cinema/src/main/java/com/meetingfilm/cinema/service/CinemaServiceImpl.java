package com.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.cinema.controller.VO.CinemaDescribeRespVO;
import com.meetingfilm.cinema.controller.VO.CinemaSavedReqVO;
import com.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.meetingfilm.cinema.dao.mapper.MoocCinemaTMapper;
import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.util.ToolUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CinemaServiceImpl implements CinemaServiceAPI{

    @Resource
    private MoocCinemaTMapper cinemaTMapper;

    @Override
    public void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException {

        MoocCinemaT cinemaT = new MoocCinemaT();

        cinemaT.setCinemaName(reqVO.getCinemaName());
        cinemaT.setCinemaPhone(reqVO.getCinemaTele());
        cinemaT.setBrandId(ToolUtils.str2Int(reqVO.getBrandId()));
        cinemaT.setAreaId(ToolUtils.str2Int(reqVO.getAreaId()));
        cinemaT.setHallIds(reqVO.getHallTypeIds());
        cinemaT.setImgAddress(reqVO.getCinemaImgAddress());
        cinemaT.setCinemaAddress(reqVO.getCinemaAddress());
        cinemaT.setMinimumPrice(ToolUtils.str2Int(reqVO.getCinemaPrice()));

        cinemaTMapper.insert(cinemaT);

    }

    @Override
    public IPage<CinemaDescribeRespVO> cinemasDescribe(int nowPage, int pageSize) {

//        Page<CinemaDescribeRespVO> page = new Page<>(nowPage,pageSize);

        IPage<CinemaDescribeRespVO> moocCinemaTIPage = cinemaTMapper.describeCinemas(new Page<>(nowPage,pageSize));
//                                                     filmTMapper.describeFilms(new Page<>(nowPage,pageSize));
        return moocCinemaTIPage;
    }
}
