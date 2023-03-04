package com.example.uniappspringboot.Controller;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.UserDao;
import com.example.uniappspringboot.Domain.EmailCode;
import com.example.uniappspringboot.Service.EmailCodeService;
import com.example.uniappspringboot.Service.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static com.example.uniappspringboot.Util.RandomUtil.getRandomNumber;

@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/email")
@ResponseBody
public class EmailCodeController {


    @Autowired
    private EmailCodeService emailCodeService;

    @Autowired
    private UserDao userDao;

    @PostMapping("/send")
    public R sendEmail(EmailCode emailCode){return emailCodeService.addEmail(emailCode);}

    @PostMapping("/login")
    public R loginEmail(EmailCode emailCode){return emailCodeService.delEmail(emailCode);}


}
