package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.Privileges;
import com.example.uniappspringboot.Domain.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {


    R setUser(User user);//注册 (token)

    R Registration(User user); //用户注册信息

    R PLogin(User user);//登录

    R CheckToken(User user);//校验token是否过期

    R SelectUserIofo(User user); //查询单个用户信息

    R getlogin();//查询信息

    R Avatar(User user);//头像上传

    R Detailed_address(User user); //用户地址  和详细地址

    //修改用户信息
    R setUserInfo(User user) throws IOException;

    //分页查询
    R setUserPages(User user);

    //修改用户信息
    R selPurview(User user);


    //查询所有用户坐标
    R selCoordinate();
}