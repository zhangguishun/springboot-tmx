package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.Software;

public interface SoftwareService {
    //添加软件
    R addSoft(Software software);

    //删除软件商品
    R delSoft(Software software);

    //修改软件商品(修改)
    R setSoft(Software software);

    //查询软件商品(查询)
    R selSoft(Software software);

    //查询软件商品(查询多条)
    R selSoftsGet();
}
