package com.meetingfilm.film.dao.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.film.controller.vo.DescribeFilmRespVO;
import com.meetingfilm.film.controller.vo.DescribeFilmsRespVO;
import com.meetingfilm.film.dao.entity.MoocFilmT;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author lsy
 * @since 2023-03-08
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {
    IPage<DescribeFilmsRespVO> describeFilms(Page<DescribeFilmsRespVO> page);

    DescribeFilmRespVO describeFilmById(@Param("filmId") String filmId);
}
