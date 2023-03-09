package com.meetingfilm.user.dao;

import com.meetingfilm.user.BackendUserApplicationTests;
import com.meetingfilm.user.dao.entity.MoocBackendUserT;
import com.meetingfilm.user.dao.mapper.MoocBackendUserTMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.meetingfilm.utils.common.util.MD5Util;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;


public class UserTest extends BackendUserApplicationTests {
    @Resource
    private MoocBackendUserTMapper backendUser;

    //添加后台运维用户功能
    @Test
    public void add(){
        MoocBackendUserT user = new MoocBackendUserT();
        user.setUserName("admin5");
        user.setUserPwd(MD5Util.encrypt("admin121"));
        user.setUserPhone("13555555555");

        backendUser.insert(user);


    }
    @Test
    public void select(){
        //根据主键查询
//        MoocBackendUserT moocBackendUserT = backendUser.selectById(2);
//        System.out.println("moocBackendUserT"+moocBackendUserT);

        //查询列表
        List<MoocBackendUserT> user = backendUser.selectList(null);
        user.stream().forEach(System.out::println);

        //查询列表带条件
//        QueryWrapper wrapper = new QueryWrapper();
//        wrapper.eq("user_name","admin4");
//
//        List<MoocBackendUserT> user = backendUser.selectList(wrapper);
//        user.stream().forEach(System.out::println);
    }

}
