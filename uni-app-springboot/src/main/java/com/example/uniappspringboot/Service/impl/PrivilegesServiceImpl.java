package com.example.uniappspringboot.Service.impl;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.PrivilegesDao;
import com.example.uniappspringboot.Domain.Privileges;
import com.example.uniappspringboot.Service.PrivilegesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivilegesServiceImpl implements PrivilegesService {
    @Autowired
    private PrivilegesDao privilegesDao;

    @Override //权限查询(全部)
    public R selPrivileges(){
        R r=new  R();
        List res=privilegesDao.selectList(null);
        r.setData(res);
        r.setCode(String.valueOf(200));
        r.setMsg("查询成功");
        return r;
    }

    @Override //新增权限
    public R addPrivileges(Privileges privileges){
        R r=new  R();
       int res= privilegesDao.insert(privileges);
       if (res==1){
           r.setData(res);
           r.setCode(String.valueOf(200));
           r.setMsg("添加成功");
       }else {
           r.setData(res);
           r.setCode(String.valueOf(203));
           r.setMsg("添加失败");
       }

        return r;
    }

}
