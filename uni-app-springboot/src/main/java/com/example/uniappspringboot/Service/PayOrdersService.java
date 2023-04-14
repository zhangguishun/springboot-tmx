package com.example.uniappspringboot.Service;

import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.PayOrders;

public interface PayOrdersService {
    //新增加订单
    R addPayOrdersDao(PayOrders payOrders);
}
