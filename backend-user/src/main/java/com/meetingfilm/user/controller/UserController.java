package com.meetingfilm.user.controller;


import com.meetingfilm.user.service.UserServiceAPI;
import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.util.JwtTokenUtil;
import com.meetingfilm.utils.common.vo.BaseResponseVO;
import com.meetingfilm.user.controller.vo.LoginReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//用户表现层
@RestController
@RequestMapping(value ="/user")
public class UserController {

    @Autowired
    private UserServiceAPI serviceAPI;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResponseVO login(@RequestBody LoginReqVO reqVO) throws CommonServiceException {

        //数据验证
        reqVO.checkParam();

        //验证用户名和密码是否正确
        String userId = serviceAPI.checkUserLogin(reqVO.getUsername(),reqVO.getPassword());

        JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
        String randomKey = jwtTokenUtil.getRandomKey();
        String token = jwtTokenUtil.generateToken(userId, randomKey);

        //randomKey token
        Map<String,String> result = new HashMap<>();
        result.put("randomKey",randomKey);
        result.put("token",token);

        return BaseResponseVO.success(result);
    }
}
