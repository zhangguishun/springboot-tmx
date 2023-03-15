package com.example.uniappspringboot.Controller;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.EmailCode;
import com.example.uniappspringboot.Domain.User;
import com.example.uniappspringboot.Service.EmailCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/email")
@ResponseBody
public class EmailCodeController {
    @Autowired
    private EmailCodeService emailCodeService;

    @PostMapping("/send")
    public R sendEmail(User user){R res=emailCodeService.addEmail(user);return res;}
    @PostMapping("/login")
    public R loginEmail(EmailCode emailCode){return emailCodeService.delEmail(emailCode);}

}
