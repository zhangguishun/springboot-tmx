package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    R Registration(User user); //用户注册信息

    R PLogin(User user);//登录

    R CheckToken(User user);//校验token是否过期

    R SelectUserIofo(User user); //查询单个用户信息

    R getlogin(User user);//查询信息

    R Avatar(User user);//头像上传

    R Detailed_address(User user); //用户地址  和详细地址
}