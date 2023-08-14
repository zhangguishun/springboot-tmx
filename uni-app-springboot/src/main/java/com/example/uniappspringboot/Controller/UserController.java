package com.example.uniappspringboot.Controller;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.User;
import com.example.uniappspringboot.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(tags = "用户信息")
@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/user")
@ResponseBody
public class UserController {
    @Autowired //接口
    private UserService userService;

    @PostMapping("/set（uniapp）")
    @ApiOperation("修改用户信息（传啥改啥）")
    public R setUser(User user){return userService.setUser(user);}//token

    @PostMapping("/PostReg") //注册用户信息
    @ApiOperation("注册用户信息")
    public R PostRegistration(User user){return userService.Registration(user);}

    @GetMapping("/getlogin") //查询用户信息（全部）
    @ApiOperation("查询用户信息（全部）")
    public R GetLogin(){return userService.getlogin();}

    @PostMapping("PostLogin")//登录
    @ApiOperation("登录")
    public R PostToLogin(User user){return userService.PLogin(user);}

    @PostMapping("/checklogin")//自动登录校验
    @ApiOperation("自动登录校验")
    public R CheckLogin(User user){return userService.CheckToken(user);}

    @PostMapping("/CheckUserInfo")//查询用户信息
    @ApiOperation("查询用户信息（单独）")
    public R CheckUinfo(User user){return userService.SelectUserIofo(user);}

    @PostMapping("/address") //用户地址
    @ApiOperation("修改用户地址（不推荐）")
    public R Detailed_Address(User user){return userService.Detailed_address(user);}

    @PostMapping("avatar")//头像上传
    @ApiOperation("用户头像上传（不推荐）")
    public R PostAvatar(User user){return userService.Avatar(user);}

    @PostMapping("/setInfo")
    @ApiOperation("修改用户信息(PC)")
    public  R PostSetInfo(User user) throws IOException {return userService.setUserInfo(user);}

    @PostMapping("/selUserPage")
    @ApiOperation("后台用户查询（分页PC）")
    public  R postUserPage(User user){return userService.setUserPages(user);}

    @PostMapping("/purview")
    @ApiOperation("综合网关于我们展示人员")
    public  R PostPurview(User user){return userService.selPurview(user);}

    @GetMapping("/coordinate")
    @ApiOperation("查询所有坐标点")
    public R GetCoordinate() throws IOException{return userService.selCoordinate();}
}
