package com.atguigu.gmall.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.UserInfo;
import com.atguigu.gmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class UserController {

    @Reference
    private UserService userService;

    @RequestMapping("/userinfolist")
    @ResponseBody
    public List<UserInfo> userInfoList(){
        List<UserInfo> userInfoList = userService.getUserInfoList();
        return userInfoList;
    }
    @RequestMapping("/userinfo/{id}")
    @ResponseBody
    public UserInfo userInfoList(@PathVariable("id") Integer id){
        UserInfo userInfo = userService.getByPrimaryKey(id);
        return userInfo;
    }
    @RequestMapping("/remove/userinfo/{id}")
    public void removeUserinfo(@PathVariable("id") Integer id){
        userService.removeUserInfo(id);
    }
}


