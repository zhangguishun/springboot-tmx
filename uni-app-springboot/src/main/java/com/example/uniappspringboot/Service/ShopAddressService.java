package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.ShopAddress;

public interface ShopAddressService {

    //新增收货地址
    R setAddress(ShopAddress shopAddress);

    //删除收货地址
    R delAddress(ShopAddress shopAddress);
}
