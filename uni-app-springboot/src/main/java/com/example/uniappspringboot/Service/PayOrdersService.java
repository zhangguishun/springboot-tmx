package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.PayOrders;

public interface PayOrdersService {
    //新增加订单
    R addPayOrdersDao(PayOrders payOrders);

    //删除订单
    R delPayOdersDao(PayOrders payOrders);


    //更新订单（单个查询）
    R setPayOdersDao(PayOrders payOrders);

    //查询用户所有的订单
    R selsPayOdersDao(PayOrders payOrders);

    //查询所有订单
    R selAdminOdersDao(PayOrders payOrders);
}
