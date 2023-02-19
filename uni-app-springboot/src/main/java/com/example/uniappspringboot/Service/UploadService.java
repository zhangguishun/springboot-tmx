package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {


    //文件上传
    R fileopload(MultipartFile file);

    //删除文件
    R deltimg(String fileName);
}
