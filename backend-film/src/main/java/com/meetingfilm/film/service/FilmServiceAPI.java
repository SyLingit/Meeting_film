package com.meetingfilm.film.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.meetingfilm.utils.common.exception.CommonServiceException;

//影片逻辑层
public interface FilmServiceAPI {

    //获取演员列表
    IPage<DescribeActorsRespVO> describeActors(int nowPage,int pageSize) throws CommonServiceException;

    //获取电影列表
    IPage<DescribeFilmsRespVO> describeFilms(int nowPage, int pageSize) throws CommonServiceException;

    //根据编号获取电影信息
    DescribeFilmRespVO describeFilmById(String filmId) throws CommonServiceException;

    //保存电影信息
    void saveFilm(FilmSavedReqVO filmSavedReqVO) throws CommonServiceException;

}
