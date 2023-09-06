package com.kaifamiao.music.controller;

import com.kaifamiao.music.domain.User;
import com.kaifamiao.music.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
public class UserController {

    @Resource
    private UserService userService;

//    登录
    @PostMapping("/login")
    public User loginUser(@RequestBody User user, HttpServletRequest request){
        return userService.selectLogin(user);
    }

//    注册
    @PostMapping("/register")
    public Integer registerUser(@RequestBody User user){
        return userService.insertSelective(user);
    }
//    修改用户信息
    @PostMapping("/updateUser")
    public Integer updateUser(@RequestBody User user) {
        return userService.updateByPrimaryKeySelective(user);
    }
}
