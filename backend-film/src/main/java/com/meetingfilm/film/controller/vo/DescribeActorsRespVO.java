package com.meetingfilm.film.controller.vo;


import com.meetingfilm.utils.common.vo.BaseResponseVO;
import lombok.Data;
//查询演员列表接口返回对象
@Data
public class DescribeActorsRespVO {

    private Integer actorId;
    private String actorName;

}
