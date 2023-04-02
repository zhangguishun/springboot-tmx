package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.Privileges;

public interface PrivilegesService {

    //权限查询(全部)
    R selPrivileges();

    //新增权限
    R addPrivileges(Privileges privileges);
}
