package com.meetingfilm.hall.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Maps;
import com.meetingfilm.hall.controller.vo.HallSaveReqVO;
import com.meetingfilm.hall.controller.vo.HallsReqVO;
import com.meetingfilm.hall.controller.vo.HallsRespVO;
import com.meetingfilm.hall.service.HallServiceAPI;
import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.vo.BaseResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/halls")
public class HallController {

    @Autowired
    private HallServiceAPI hallServiceAPI;

    /**
     * @Description: 获取播放厅列表
     * @param hallsReqVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public BaseResponseVO describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {

        hallsReqVO.checkParam();

        IPage<HallsRespVO> page = hallServiceAPI.describeHalls(hallsReqVO);

        Map<String, Object> halls = describePageResult(page, "halls");

        return BaseResponseVO.success(halls);

    }    /**
     * @Description: 新增播放厅
     * @param：hallsReqVO
     * @return
     * @throws CommonServiceException
     */
    @RequestMapping(value = "/hall:add",method = RequestMethod.POST)
    public BaseResponseVO saveHall(@RequestBody HallSaveReqVO hallSaveReqVO) throws CommonServiceException {

        hallSaveReqVO.checkParam();

        hallServiceAPI.saveHall(hallSaveReqVO);

        return BaseResponseVO.success();

    }



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
