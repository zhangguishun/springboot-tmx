package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.EmailCode;
import com.example.uniappspringboot.Domain.User;

public interface EmailCodeService {

    //新增邮箱
    R addEmail(User user);

    //删除邮箱验证
    R delEmail(EmailCode emailCode);
}
