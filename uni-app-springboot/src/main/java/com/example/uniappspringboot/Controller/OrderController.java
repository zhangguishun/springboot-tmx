package com.example.uniappspringboot.Controller;


import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.Order;
import com.example.uniappspringboot.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/order")
@ResponseBody
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/add") //新增订单
    public R addPostOrder(Order order) {
        return orderService.addOrder(order);
    }

    @PostMapping("/del") //删除订单
    public R delPostOrder(Order order) {
        return orderService.delOrder(order);
    }

    @PostMapping("/set") //修改订单
    public R setPostOrder(Order order) {
        return orderService.setOrder(order);
    }

    @PostMapping("/sel") //查询订单
    public R selPostOrder(Order order) {
        return orderService.selOrder(order);
    }
}
