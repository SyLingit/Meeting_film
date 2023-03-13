package com.meetingfilm.film.controller;

//影片模块表现层

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.meetingfilm.film.controller.vo.DescribeActorsRespVO;
import com.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.meetingfilm.film.controller.vo.FilmSavedReqVO;
import com.meetingfilm.film.service.FilmServiceAPI;
import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.vo.BasePageVO;
import com.meetingfilm.utils.common.vo.BaseResponseVO;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/films")
public class FilmController {
    @Autowired
    private FilmServiceAPI filmServiceAPI;

    //获取演员列表
    @RequestMapping(value = "/actors",method = RequestMethod.GET)
    public BaseResponseVO describeActors(BasePageVO basePageVO) throws CommonServiceException {

        //检查入参
        basePageVO.checkParam();

        //调用逻辑层，获取返回参数
         IPage<DescribeActorsRespVO> results = filmServiceAPI.describeActors(basePageVO.getNowPage(),basePageVO.getPageSize());

         Map<String,Object> actors = describePageResult(results,"actors");
        return BaseResponseVO.success(actors);
    }


    //获取电影列表
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO describeFilms(BasePageVO basePageVO) throws CommonServiceException {

        //检查入参
        basePageVO.checkParam();

        //调用逻辑层，获取返回参数
        IPage<DescribeFilmsRespVO> results = filmServiceAPI.describeFilms(basePageVO.getNowPage(),basePageVO.getPageSize());

        Map<String,Object> films = describePageResult(results,"films");
        return BaseResponseVO.success(films);
    }


    //根据电影id获取电影信息
    @RequestMapping(value = "/{filmId}",method = RequestMethod.GET)
    public BaseResponseVO describeFilmById(@PathVariable("filmId")String filmId) throws CommonServiceException {

        DescribeFilmRespVO describeFilmRespVO = filmServiceAPI.describeFilmById(filmId);
        if(describeFilmRespVO == null){
            return BaseResponseVO.success();
        }else {
            return BaseResponseVO.success(describeFilmRespVO);
        }

    }


    //影片新增
    @RequestMapping(value = "/film:add",method = RequestMethod.POST)
    public BaseResponseVO saveFilmInfo(@RequestBody FilmSavedReqVO filmSavedReqVO) throws CommonServiceException {

        filmServiceAPI.saveFilm(filmSavedReqVO);

        return BaseResponseVO.success();

    }

    //获取分页对象的公共接口
    private Map<String,Object> describePageResult(IPage page, String title){

        Map<String,Object> result = Maps.newHashMap();

        result.put("totalSize",page.getTotal());
        result.put("totalPages",page.getPages());
        result.put("pageSize",page.getSize());
        result.put("nowPage",page.getCurrent());

        result.put(title, page.getRecords());

        return result;
    }
}
