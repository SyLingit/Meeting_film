package com.meetingfilm.film.service;

//影片模块实现层

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.meetingfilm.film.dao.mapper.MoocActorTMapper;
import com.meetingfilm.film.dao.mapper.MoocFilmActorTMapper;
import com.meetingfilm.film.dao.mapper.MoocFilmInfoTMapper;
import com.meetingfilm.film.dao.mapper.MoocFilmTMapper;
import com.meetingfilm.utils.common.exception.CommonServiceException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FilmServiceImpl implements FilmServiceAPI{

    @Resource
    private MoocActorTMapper actorTMapper;
    @Resource
    private MoocFilmTMapper filmTMapper;
    @Resource
    private MoocFilmInfoTMapper filmInfoTMapper;
    @Resource
    private MoocFilmActorTMapper filmActorTMapper;

    @Override
    public IPage<DescribeActorsRespVO> describeActors(int nowPage, int pageSize) throws CommonServiceException {
       //查询演员列表
        return actorTMapper.describeActors(new Page<>(nowPage,pageSize));
    }

    @Override
    public IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) throws CommonServiceException {
        return null;
    }

    @Override
    public DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException {
        return null;
    }

    @Override
    public void saveFilm(FilmSavedReqVO filmSavedReqVO) throws CommonServiceException {

    }
}
