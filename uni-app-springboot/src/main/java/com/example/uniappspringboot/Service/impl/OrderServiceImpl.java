package com.example.uniappspringboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.OrderDao;
import com.example.uniappspringboot.Domain.Order;
import com.example.uniappspringboot.Service.OrderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override //新增订单
    public R addOrder(Order order) {
       // System.out.println(order);
        R r = new R();
        int res = orderDao.insert(order);
        return getR(order, r, res);
    }

    @Override //删除订单
    public R delOrder(Order order) {
        R r = new R();
        int res = orderDao.deleteById(order);
        return getR(order, r, res);
    }

    @Override //修改订单
    public R setOrder(Order order) {
        R r = new R();
        int res = orderDao.updateById(order);
        return getR(order, r, res);
    }

    @Override //查询订单
    public R selOrder(Order order) {
        R r = new R();
        LambdaQueryWrapper<Order> sel = new LambdaQueryWrapper<Order>();
        sel.eq(Order::getOpenid, order.getOpenid());
        ArrayList res = (ArrayList) orderDao.selectList(sel);
        if (res.isEmpty()) {
            r.setCode(String.valueOf(203));
            r.setMsg("失败");
        } else {
            r.setData(res);
            r.setCode(String.valueOf(200));
            r.setMsg("成功");
        }
        return r;
    }

    @NotNull
    private R getR(Order order, R r, int res) {
        if (res == 1) {
            r.setData(order);
            r.setCode(String.valueOf(200));
            r.setMsg("成功");
        } else {
            r.setCode(String.valueOf(203));
            r.setMsg("失败");
        }
        return r;
    }

}
