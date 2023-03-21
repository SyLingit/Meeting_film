package com.meetingfilm.hall.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.hall.controller.vo.HallSaveReqVO;
import com.meetingfilm.hall.controller.vo.HallsReqVO;
import com.meetingfilm.hall.controller.vo.HallsRespVO;
import com.meetingfilm.hall.dao.entity.MoocFieldT;
import com.meetingfilm.hall.dao.entity.MoocHallFilmInfoT;
import com.meetingfilm.hall.dao.mapper.MoocFieldTMapper;
import com.meetingfilm.hall.dao.mapper.MoocHallFilmInfoTMapper;
import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.util.ToolUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@Service
@Slf4j
public class HallServiceImpl implements HallServiceAPI{

    @Resource
    private MoocFieldTMapper fieldTMapper;

    @Resource
    private MoocHallFilmInfoTMapper hallFilmInfoTMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient eurekaClient;

    /**
     * @Description: 查询影厅列表
     * @param hallsReqVO
     * @return
     * @throws CommonServiceException
     */
    @Override
    public IPage<HallsRespVO> describeHalls(HallsReqVO hallsReqVO) throws CommonServiceException {

        Page<HallsReqVO> page = new Page<>(hallsReqVO.getNowPage(),hallsReqVO.getPageSize());

        QueryWrapper queryWrapper = new QueryWrapper();
        if(ToolUtils.strIsNotNull(hallsReqVO.getCinemaId())){
            queryWrapper.eq("cinema_id",hallsReqVO.getCinemaId());
        }
        IPage<HallsRespVO> result = fieldTMapper.describeHalls(page, queryWrapper);
//        log.info("result: {}", result);
        return result;
    }

    /**
     * @Description: 保存影厅信息
     * @param
     * @return
     * @throws CommonServiceException
     */
    @Override
    public void saveHall(HallSaveReqVO reqVO) throws CommonServiceException {

        //TODO 加try-catch模块
        //播放厅的列表数据
        MoocFieldT field = new MoocFieldT();

        field.setCinemaId(ToolUtils.str2Int(reqVO.getCinemaId()));
        field.setFilmId(ToolUtils.str2Int(reqVO.getFilmId()));
        field.setBeginTime(reqVO.getBeginTime());
        field.setEndTime(reqVO.getEndTime());
        field.setHallId(ToolUtils.str2Int(reqVO.getHallTypeId()));
        field.setHallName(reqVO.getHallName());
        field.setPrice(ToolUtils.str2Int(reqVO.getFilmPrice()));


        fieldTMapper.insert(field);

        //播放厅对应的影片数据,影片冗余数据，缓存里有一份
        MoocHallFilmInfoT hallFilmInfo = describeFilmInfo(reqVO.getFilmId());

        hallFilmInfoTMapper.insert(hallFilmInfo);


    }
    private MoocHallFilmInfoT describeFilmInfo(String filmId)throws CommonServiceException{

        //GET REGISTER
        ServiceInstance choose = eurekaClient.choose("film-service");
        //组织调用参数
        String hostname = choose.getHost();
        int port = choose.getPort();

        String uri = "/films/"+filmId;

        String url = "http://"+hostname+":"+port+uri;

        //通过restTemplate调用影片服务
        JSONObject baseResponseVO = restTemplate.getForObject(url, JSONObject.class);

        //解析返回值
        JSONObject dataJson = baseResponseVO.getJSONObject("data");
        //组织参数
        MoocHallFilmInfoT hallFilmInfo = new MoocHallFilmInfoT();

        hallFilmInfo.setFilmId(dataJson.getIntValue("filmId"));
        hallFilmInfo.setFilmName(dataJson.getString("filmName"));
        hallFilmInfo.setFilmLength(dataJson.getString("filmLength"));
        hallFilmInfo.setFilmCats(dataJson.getString("filmCats"));
        hallFilmInfo.setActors(dataJson.getString("actors"));
        hallFilmInfo.setImgAddress(dataJson.getString("imgAdress"));

        return hallFilmInfo;
    }
}
