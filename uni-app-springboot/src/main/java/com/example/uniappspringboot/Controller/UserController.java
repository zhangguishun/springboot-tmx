package com.example.uniappspringboot.Controller;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.User;
import com.example.uniappspringboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Autowired //接口
    private UserService userService;

    @PostMapping("/set")
    public R setUser(User user){return userService.setUser(user);}//token

    @PostMapping("/PostReg") //注册用户信息
    public R PostRegistration(User user){return userService.Registration(user);}

    @GetMapping("/getlogin") //查询用户信息
    public R GetLogin(User user){return userService.getlogin(user);}

    @PostMapping("PostLogin")//登录
    public R PostToLogin(User user){return userService.PLogin(user);}

    @PostMapping("/checklogin")//自动登录校验
    public R CheckLogin(User user){return userService.CheckToken(user);}

    @PostMapping("/CheckUserInfo")//查询用户信息
    public R CheckUinfo(User user){return userService.SelectUserIofo(user);}

    @PostMapping("/address") //用户地址
    public R Detailed_Address(User user){return userService.Detailed_address(user);}

    @PostMapping("avatar")//头像上传
    public R PostAvatar(User user){return userService.Avatar(user);}

    @PostMapping("/setInfo")
    public  R PostSetInfo(User user){return userService.setUserInfo(user);}

    @PostMapping("/purview")
    public  R PostPurview(User user){return userService.selPurview(user);}



}
