package com.meetingfilm.cinema.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.cinema.controller.VO.CinemaSavedReqVO;
import com.meetingfilm.cinema.controller.VO.DescribeCinemasRespVO;
import com.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.meetingfilm.cinema.dao.mapper.MoocCinemaTMapper;
import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.util.ToolUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 影院实现类
 */

@Service
public class CinemaServiceImpl implements CinemaServiceAPI{
    @Resource
    private MoocCinemaTMapper cinemaTMapper;

    @Override
    public void saveCinema(CinemaSavedReqVO reqVO) throws CommonServiceException {

        MoocCinemaT cinema = new MoocCinemaT();

        cinema.setCinemaName(reqVO.getCinemaName());
        cinema.setCinemaPhone(reqVO.getCinemaTele());
        cinema.setBrandId(ToolUtils.str2Int(reqVO.getBrandId()));
        cinema.setAreaId(ToolUtils.str2Int(reqVO.getAreaId()));
        cinema.setHallIds(reqVO.getHallTypeIds());
        cinema.setImgAddress(reqVO.getCinemaImgAddress());
        cinema.setCinemaAddress(reqVO.getCinemaAddress());
        cinema.setMinimumPrice(ToolUtils.str2Int(reqVO.getCinemaPrice()));

        cinemaTMapper.insert(cinema);


    }

    @Override
    public IPage<DescribeCinemasRespVO> describeCinemas(int nowPage, int pageSize) throws CommonServiceException {

        Page<MoocCinemaT> page = new Page<>(nowPage,pageSize);
        IPage<MoocCinemaT> moocCinemaTIPage = cinemaTMapper.selectPage(page,null);

        moocCinemaTIPage.getRecords();
        List<DescribeCinemasRespVO> list = new ArrayList<>();
        for(MoocCinemaT m: moocCinemaTIPage.getRecords()){

            DescribeCinemasRespVO describeCinemasRespVO = new DescribeCinemasRespVO();
            describeCinemasRespVO.setAreaId(String.valueOf(m.getAreaId()));
            describeCinemasRespVO.setBrandId(String.valueOf(m.getBrandId()));
            describeCinemasRespVO.setHallTypeIds(m.getHallIds());
            describeCinemasRespVO.setCinemaName(m.getCinemaName());
            describeCinemasRespVO.setCinemaAddress(m.getCinemaAddress());
            describeCinemasRespVO.setCinemaTele(m.getCinemaPhone());
            describeCinemasRespVO.setCinemaImgAddress(m.getImgAddress());
            describeCinemasRespVO.setCinemaPrice(String.valueOf(m.getMinimumPrice()));

            list.add(describeCinemasRespVO);
        }
        IPage<DescribeCinemasRespVO> respVOIPage = new Page<>();
        respVOIPage.setRecords(list);
        respVOIPage.setTotal(moocCinemaTIPage.getTotal());
        respVOIPage.setPages(moocCinemaTIPage.getPages());
        respVOIPage.setSize(moocCinemaTIPage.getSize());
        respVOIPage.setCurrent(moocCinemaTIPage.getCurrent());



        return respVOIPage;
    }
}
