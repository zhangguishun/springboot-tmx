package com.example.uniappspringboot.Controller;


import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Service.UploadService;
import com.example.uniappspringboot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/upload")
@ResponseBody
public class UploadController {
    @Autowired //接口
    private UploadService uploadService;

    @PostMapping("/avatar")//文件上传接口
    public  R  Upload(@RequestParam("file")MultipartFile file){return uploadService.fileopload(file);}

    @PostMapping("/deleteimg") //删除服务器文件
    public  R deltinfo(String fileName ){return uploadService.deltimg(fileName); }
}
