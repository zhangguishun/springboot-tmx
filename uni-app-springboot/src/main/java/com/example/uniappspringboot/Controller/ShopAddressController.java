package com.example.uniappspringboot.Controller;


import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.ShopAddress;
import com.example.uniappspringboot.Service.ShopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin //开放前端的跨域访问
@RestController
@RequestMapping("/address")
@ResponseBody
public class ShopAddressController {
    @Autowired
    private ShopAddressService shopAddressService;

    @PostMapping("/add")//新增
    public R postAddShopAddress(ShopAddress shopAddress) {
        return shopAddressService.addAddress(shopAddress);
    }

    @PostMapping("/del")//删除
    public R postDelShopAddress(ShopAddress shopAddress) {
        return shopAddressService.delAddress(shopAddress);
    }

    @PostMapping("/set")//修改
    public R postSetShopAddress(ShopAddress shopAddress) {
        return shopAddressService.setAddress(shopAddress);
    }

    @PostMapping("/sel")//查询
    public R postSelShopAddress(ShopAddress shopAddress) {
        return shopAddressService.selAddress(shopAddress);
    }
}
