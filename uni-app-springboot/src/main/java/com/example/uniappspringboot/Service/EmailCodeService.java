package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.EmailCode;

public interface EmailCodeService {

    //新增邮箱
    R addEmail(EmailCode emailCode);

    //删除邮箱验证
    R delEmail(EmailCode emailCode);
}
