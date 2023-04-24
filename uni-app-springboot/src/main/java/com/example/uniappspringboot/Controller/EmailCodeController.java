package com.example.uniappspringboot.Controller;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.EmailCode;
import com.example.uniappspringboot.Domain.User;
import com.example.uniappspringboot.Service.EmailCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(tags = "邮箱模块")
@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/email")
@ResponseBody
public class EmailCodeController {
    @Autowired
    private EmailCodeService emailCodeService;

    @PostMapping("/send")
    @ApiOperation("发送QQ邮箱验证码")
    public R sendEmail(User user){R res=emailCodeService.addEmail(user);return res;}
    @PostMapping("/login")
    @ApiOperation("QQ邮箱验证码登录")
    public R loginEmail(EmailCode emailCode){return emailCodeService.delEmail(emailCode);}

}
