package com.example.uniappspringboot.Controller;


import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.PayOrders;
import com.example.uniappspringboot.Domain.Shopping;
import com.example.uniappspringboot.Service.PayOrdersService;
import com.example.uniappspringboot.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 支付创建订单
 * 更新订单
 * 删除订单
 */
@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/payOrders")
@ResponseBody
public class PayOrdersController {
    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    private PayOrdersService payOrdersService;

    @PostMapping("/addOrders") //新增订单
    public R addPayOrdersService(PayOrders payOrders){return payOrdersService.addPayOrdersDao(payOrders);}

    @PostMapping("/addShopping") //新增商品
    public R addShoppingService(Shopping shopping){return shoppingService.addShoppingDao(shopping);}

    @DeleteMapping("/delShopping")//删除商品
    public R delShoppingService(Shopping shopping){return shoppingService.delShoppingDao(shopping);}

    @PostMapping("/downloadSoft")//校验订单，返回下载地址
    public R downloadSoftCheck(PayOrders payOrders){return payOrdersService.selsPayOdersDao(payOrders);}

}
