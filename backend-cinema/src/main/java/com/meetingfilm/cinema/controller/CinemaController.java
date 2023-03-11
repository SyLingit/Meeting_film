package com.meetingfilm.cinema.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.meetingfilm.cinema.controller.VO.CinemaDescribeRespVO;
import com.meetingfilm.cinema.controller.VO.CinemaSavedReqVO;
import com.meetingfilm.cinema.dao.entity.MoocCinemaT;
import com.meetingfilm.cinema.service.CinemaServiceAPI;
import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.vo.BasePageVO;
import com.meetingfilm.utils.common.vo.BaseResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cinemas")
public class CinemaController {

    @Autowired
    private CinemaServiceAPI cinemaServiceAPI;

    @RequestMapping(value = "/cinema:add",method = RequestMethod.POST)
    public BaseResponseVO saveCinema(@RequestBody CinemaSavedReqVO cinemaSavedReqVO) throws CommonServiceException {

        //数据验证
        cinemaSavedReqVO.checkParam();

        cinemaServiceAPI.saveCinema(cinemaSavedReqVO);

        return BaseResponseVO.success();

    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO describeCinemas(BasePageVO basePageVO)throws CommonServiceException{

        IPage<CinemaDescribeRespVO> cinemaDescribeRespVOIPage = cinemaServiceAPI.cinemasDescribe(basePageVO.getNowPage(), basePageVO.getPageSize());


        //TODO 调用公共的分页返回方法
        Map<String,Object> cinemas = describePageResult(cinemaDescribeRespVOIPage,"cinemas");

        if(cinemaDescribeRespVOIPage.getSize() == 0){
           return BaseResponseVO.success();
        }else{
            return BaseResponseVO.success(cinemas);
        }

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


//        try{
//            IPage<CinemaDescribeRespVO> page = cinemaServiceAPI.cinemasDescribe(nowPage,pageSize);
//            IPage<MoocCinemaT> page = cinemaServiceAPI.cinemasDescribe(nowPage,pageSize);
//
//            Map<String,Object> result = Maps.newHashMap();
//            result.put("nowPage", page.getCurrent());
//            result.put("pageSize", page.getSize());
//            result.put("totalPage", page.getPages());
//            result.put("totalSize", page.getTotal());
//
//            if(page.getSize() == 0){
//                baseResponseVO = BaseResponseVO.success();
//            }else {
//                List<CinemaSavedReqVO> cinemas = page.getRecords().stream().map((entity)->{
//                    return CinemaSavedReqVO.pojo2VO(entity);
//                }).collect(Collectors.toList());
//                result.put("cinemas",cinemas);
//                baseResponseVO = BaseResponseVO.success(result);
//            }
//
//
//        }catch (CommonServiceException e){
//            baseResponseVO = BaseResponseVO.serviceException(e);
//        }