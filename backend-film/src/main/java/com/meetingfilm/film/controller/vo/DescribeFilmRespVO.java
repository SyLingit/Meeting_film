package com.meetingfilm.film.controller.vo;

//根据主键获取影片信息对象

import lombok.Data;

@Data
public class DescribeFilmRespVO {

    private String filmId;
    private String filmName;
    private String filmLength;
    private String filmCats;
    private String actors;
    private String imgAddress;
    private String subAddress;


}
