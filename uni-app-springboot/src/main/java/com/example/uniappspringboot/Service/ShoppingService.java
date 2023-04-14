package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.Shopping;

public interface ShoppingService {
    //添加商品
    R addShoppingDao(Shopping shopping);

    //删除订单
    R delShoppingDao(Shopping shopping);
}
