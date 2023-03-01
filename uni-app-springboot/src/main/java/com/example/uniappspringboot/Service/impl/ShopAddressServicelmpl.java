package com.example.uniappspringboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.ShopAddressDao;
import com.example.uniappspringboot.Domain.ShopAddress;
import com.example.uniappspringboot.Domain.User;
import com.example.uniappspringboot.Service.ShopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopAddressServicelmpl implements ShopAddressService {
    @Autowired
    private ShopAddressDao shopAddressDao;

    @Override //新增收货地址
    public R setAddress(ShopAddress shopAddress) {
        R r = new R();
        int res= shopAddressDao.insert(shopAddress);
        if ( res== 1) {
            r.setCode(String.valueOf(200));
            r.setMsg("添加成功");
        } else {
            r.setCode(String.valueOf(203));
            r.setMsg("添加失败");
        }
        return r;
    }

    @Override //删除收货地址
    public R delAddress(ShopAddress shopAddress) {
        R r = new R();
        int del=shopAddressDao.deleteById(shopAddress.getId());
        if ( del== 1) {
            r.setCode(String.valueOf(200));
            r.setMsg("删除成功");
        } else {
            r.setCode(String.valueOf(203));
            r.setMsg("删除失败");
        }
        return r;
    }

}
