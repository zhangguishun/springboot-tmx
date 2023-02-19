package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.YzmUserCode;

public interface YzmCodeService {
    //验证码获取和判断
    R getYzmCode(YzmUserCode yzmUserCode);

    //验证码存入
    R postYzmCode(String yzmCode);

    //清除所有code
    R DeletCode();
}
