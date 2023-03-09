package com.meetingfilm.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import com.meetingfilm.utils.common.exception.CommonServiceException;
import com.meetingfilm.utils.common.util.MD5Util;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//用户模块业务实现
@Service
public class UserServiceImpl implements UserServiceAPI {

    @Resource
    private MoocBackendUserTMapper userMapper;

    @Override
    public String checkUserLogin(String username, String password) throws CommonServiceException {
        //根据用户名获取用户信息
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name",username);

        //避免数据出问题
        List<MoocBackendUserT> list = userMapper.selectList(queryWrapper);
        MoocBackendUserT user = null;
        if(list != null && list.size()>0){
            user = list.stream().findFirst().get();
        }else {
            throw new CommonServiceException(404,"invalid username");
        }
        //验证密码是否正确【密码要做MD5加密后才能验证是否匹配】
        String encrypt = MD5Util.encrypt(password);
        if(!encrypt.equals(user.getUserPwd())){
            throw new CommonServiceException(500,"invalid password");
        }else {
            return user.getUuid()+"";
        }
    }
}
