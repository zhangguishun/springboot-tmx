package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.Order;

public interface OrderService {
    //新增订单
    R addOrder(Order order);

    //删除订单
    R delOrder(Order order);

    //修改订单
    R setOrder(Order order);

    //查询订单
    R selOrder(Order order);
}
