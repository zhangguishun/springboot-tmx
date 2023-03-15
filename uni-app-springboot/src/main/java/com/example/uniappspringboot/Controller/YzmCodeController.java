package com.example.uniappspringboot.Controller;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.YzmUserCode;
import com.example.uniappspringboot.Service.YzmCodeService;
import com.google.code.kaptcha.Producer;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/code")
@ResponseBody
public class YzmCodeController {

    @Autowired //验证码
    private Producer producer;

    @Autowired
    private YzmCodeService yzmCodeService;

    @GetMapping("/GetCode") //获取验证码
    public ServletOutputStream getimg(HttpServletResponse response) throws IOException {
        String text= producer.createText();//验证码内容
        yzmCodeService.postYzmCode(text);
        BufferedImage image=producer.createImage(text);//验证码图片流
        ServletOutputStream out =response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        IOUtils.closeQuietly(out);
        return out;
    }

    @PostMapping("/YzCode") //校验验证码
    public R postTextCode(YzmUserCode yzmUserCode){
        return yzmCodeService.getYzmCode(yzmUserCode);
    }

    @GetMapping("/DeletCode")//清除所有验证码记录
    public R DeletCodes(){return yzmCodeService.DeletCode();}
}