package com.meetingfilm.hall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.meetingfilm.hall.controller.vo.HallSaveReqVO;
import com.meetingfilm.hall.controller.vo.HallsReqVO;
import com.meetingfilm.hall.controller.vo.HallsRespVO;
import com.meetingfilm.utils.common.exception.CommonServiceException;

public interface HallServiceAPI {

    //获取全部播放厅接口
    IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException;

    //保存影厅信息
    void saveHall(HallSaveReqVO hallSaveReqVO) throws CommonServiceException;

}
