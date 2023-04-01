package com.example.uniappspringboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.EmailCodeDao;
import com.example.uniappspringboot.Dao.UserDao;
import com.example.uniappspringboot.Domain.EmailCode;
import com.example.uniappspringboot.Domain.User;
import com.example.uniappspringboot.Service.EmailCodeService;
import com.example.uniappspringboot.Service.IMailService;
import com.example.uniappspringboot.Util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static com.example.uniappspringboot.Util.RandomUtil.getRandomNumber;

@Service
public class EmailCodeServiceImpl implements EmailCodeService {
    @Autowired
    private EmailCodeDao emailCodeDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private IMailService iMailService;

    @Override //新增邮箱
    public R addEmail(User user){
        System.out.println(user);
        R r=new R();
        LambdaQueryWrapper<User> lqw=new  LambdaQueryWrapper<>();
        lqw.eq(User::getQqmailbox,user.getQqmailbox());
        User list=userDao.selectOne(lqw);//查询用户信息
        if (list==null){
            r.setCode(String.valueOf(500));
            r.setMsg("找不到邮箱");
            return r;
        }else {
            String emailCodes = getRandomNumber();//验证码
            EmailCode emailCode=new EmailCode();
            emailCode.setCode(emailCodes);
            emailCode.setOpenid(list.getOpenid());
            int res=emailCodeDao.insert(emailCode);//验证码存入数据库
            String accountNumber = list.getUsername();//用户名
            String emailName=list.getQqmailbox();//自己的接受邮箱
            String emailContent="TMX平台邮箱验证码";
            String emailHtml= "<div style=\"width: 100%;height: 800px; display: flex;flex-direction: column; align-items: center;background-color: #212429;\"> " +
                    "<div style=\"color: #bfbfbf;font-size: 2.3em;width: 90%;font-weight: 600;margin-top: 10%;\">" +"Dear"
                    + "<text style=\"margin-left: 5px;\">"+
                    accountNumber
                    +"</text>" +
                    "</div><div style=\"width: 90%;color: #bfbfbf;margin-top: 5%;font-size: 1em;\">" +
                    " Here is The email verification code required for your login account on the Tianmengxing integrated service platform </div>" +
                    " <div style=\"width: 90%;height: 110px;background-color: #17191c;margin-top: 10%;display: flex;align-items: center;justify-content: center;font-size: 2.5em ;font-weight: 600;color:#3a9aed;\">" +
                    emailCodes
                    + " </div><div style=\"width: 90%; margin-top: 10%;color: #bfbfbf; font-size: 1em;\">  The Tianmengxing integrated service platform code is required to complete the login. No one can access your account without also accessing this email </div>" +
                    " <div style=\"width: 90%; margin-top: 5%;color: #bfbfbf; font-size: 1em;\">" +
                    "  If you are not attempting to log in then please change your service platform password, " +
                    "and consider changing your email password as well to ensure your account security." +
                    " </div> <div style=\"width: 90%; margin-top: 5%;color: #bfbfbf; height: 60px; font-size: 1em;border-left: coral solid 0.2em;display: flex;align-items: center;\">" +
                    "<text style=\"margin-left: 20px;\">天梦星科技团队（张贵顺）</text> </div></div>";

            return iMailService.sendHtmlMail(emailName,emailContent,emailHtml);
        }

    }

    @Override //删除邮箱验证
    public R delEmail(EmailCode emailCode){
        R r = new R();
        LambdaQueryWrapper<User> lqw2 = new LambdaQueryWrapper<>();
        lqw2.eq(User::getQqmailbox, emailCode.getOpenid());
        User res = userDao.selectOne(lqw2);
        if (res==null){
            r.setCode(String.valueOf(500));
            r.setMsg("用户不存在");
            return r;
        }else {
            LambdaQueryWrapper<EmailCode> lqw = new LambdaQueryWrapper<>();
            lqw.eq(EmailCode::getOpenid,res.getOpenid());
            ArrayList resEmail = (ArrayList) emailCodeDao.selectList(lqw);
            for (int i = 0; i < resEmail.size(); i++) {
                EmailCode del = (EmailCode) resEmail.get(i);
                emailCodeDao.deleteById(del.getId());
            }
            if (resEmail.isEmpty()){
                r.setCode(String.valueOf(203));
                r.setMsg("失败 ");
            }else {
                ArrayList data=new ArrayList();
                 data.add(res.getOpenid());
                 data.add(TokenUtils.sign(res));
                r.setCode(String.valueOf(200));
                r.setMsg("成功");
                r.setData(data);
            }

        }
        return r;
    }
}
