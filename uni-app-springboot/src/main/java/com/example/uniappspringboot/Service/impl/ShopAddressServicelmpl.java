package com.example.uniappspringboot.Service.impl;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.ShopAddressDao;
import com.example.uniappspringboot.Domain.ShopAddress;
import com.example.uniappspringboot.Service.ShopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopAddressServicelmpl implements ShopAddressService {
    @Autowired
    private ShopAddressDao shopAddressDao;

    @Override //新增地址
    public R setAddress(ShopAddress shopAddress){
        R r=new R();

        return r;
    }
}
