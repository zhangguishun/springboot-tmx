package com.example.uniappspringboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.ShoppingDao;
import com.example.uniappspringboot.Dao.SoftwareDao;
import com.example.uniappspringboot.Domain.Shopping;
import com.example.uniappspringboot.Domain.Software;
import com.example.uniappspringboot.Service.ShoppingService;
import com.example.uniappspringboot.Service.SoftwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class SoftwareServicelmpl implements SoftwareService {
    @Autowired
    private SoftwareDao softwareDao;
    @Autowired
    private ShoppingService shoppingService;

    @Override //添加软件商品
    public R addSoft(Software software){
        SimpleDateFormat oderShop=new SimpleDateFormat("yyyyMMddHHmmss");

        R r =new R();

            if (software.getMembership().equals("SVIPusers")){
            Shopping addShop=new Shopping();
            addShop.setProductname(software.getTitle());//商品名称
            addShop.setProductprice(BigDecimal.valueOf(software.getPrice()));//商品价格
            addShop.setProductdescription(software.getContent());//商品描述
            addShop.setProductstock(String.valueOf(10000));//库存
            addShop.setMaintype("2");//支付还是会员支付
            addShop.setMerchantid("62167e6c-603f-4082-b846-495463f04c93");//商户id
            addShop.setBusinessstatus("1");//营业状态
            addShop.setProductimage(software.getPicture());//商品图片
            addShop.setProductlabel("soft");//商品标签
            addShop.setCategory("SVIP");//商品品类
            addShop.setPreferential(String.valueOf(10));//优惠
            addShop.setSold(String.valueOf(0));//已销售

            R resShop =  shoppingService.addShoppingDao(addShop);//
            Shopping resShopingData= (Shopping) resShop.getData();
            software.setSoftid(resShopingData.getProductid());
            int res=softwareDao.insert(software);

                if (res==1){
                    r.setCode(String.valueOf(200));
                    r.setMsg("添加成功");
                }else {
                    r.setCode(String.valueOf(203));
                    r.setMsg("添加失败");
                }


        }else
            if (software.getMembership().equals("SellingGoods")){
            Shopping addShop=new Shopping();
            addShop.setProductname(software.getTitle());//商品名称
            addShop.setProductprice(BigDecimal.valueOf(software.getPrice()));//商品价格
            addShop.setProductdescription(software.getContent());//商品描述
            addShop.setProductstock(String.valueOf(10000));//库存
            addShop.setMaintype("1");//支付还是会员支付
            addShop.setMerchantid("62167e6c-603f-4082-b846-495463f04c93");//商户id
            addShop.setBusinessstatus("1");//营业状态
            addShop.setProductimage(software.getPicture());//商品图片
            addShop.setProductlabel("soft");//商品标签
            addShop.setCategory("soft");//商品品类
            addShop.setPreferential(String.valueOf(10));//优惠
            addShop.setSold(String.valueOf(0));//已销售

            R resShop =  shoppingService.addShoppingDao(addShop);
            Shopping resShopingData= (Shopping) resShop.getData();
            software.setSoftid(resShopingData.getProductid());
            int res=softwareDao.insert(software);

                if (res==1){
                    r.setCode(String.valueOf(200));
                    r.setMsg("添加成功");
                }else {
                    r.setCode(String.valueOf(203));
                    r.setMsg("添加失败");
                }


        }
            else {
                software.setSoftid(oderShop.format(new Date()));
                int res=softwareDao.insert(software);
                if (res==1){
                    r.setCode(String.valueOf(200));
                    r.setMsg("添加成功");
                }else {
                    r.setCode(String.valueOf(203));
                    r.setMsg("添加失败");
                }
        }


        return r;
    }

    @Override //删除软件商品(单条删除)
    public R delSoft(Software software){
        R r =new R();
        int res=softwareDao.deleteById(software.getId());
        if (res==1){
            r.setCode(String.valueOf(200));
            r.setMsg("删除成功");
        }else {
            r.setCode(String.valueOf(203));
            r.setMsg("删除失败");
        }
        return r;
    }

    @Override //修改软件商品(修改)
    public R setSoft(Software software){
        R r =new R();
       int res= softwareDao.updateById(software);
        if (res==1){
            r.setData(softwareDao.selectById(software.getId()));
            r.setCode(String.valueOf(200));
            r.setMsg("修改成功");
        }else {
            r.setCode(String.valueOf(203));
            r.setMsg("修改失败");
        }
        return r;
    }

    @Override //查询软件商品(查询单条)
    public R selSoft(Software software){
        R r =new R();
      Software res= softwareDao.selectById(software.getId());
        if (res.getId() != null){
            r.setData(softwareDao.selectById(software.getId()));
            r.setCode(String.valueOf(200));
            r.setMsg("查询成功");
        }else {
            r.setCode(String.valueOf(203));
            r.setMsg("查询失败");
        }
        return r;
    }

    @Override //查询软件商品(查询多条)
    public R selSoftsGet(){
        R r =new R();
        List <Software> res = softwareDao.selectList(null);
        res.forEach((item)->{item.setAddress(null);});
        if (res.isEmpty()){
            r.setCode(String.valueOf(203));
            r.setMsg("查询失败");
        }else {
            r.setData(res);
            r.setCode(String.valueOf(200));
            r.setMsg("查询成功");
        }
        return r;
    }

    public R softDownloaad(Software software){
        R r=new R();
        LambdaQueryWrapper<Software> lwq =new LambdaQueryWrapper<>();
        HashMap<String,String> map =new HashMap<>();
        return r;
    }
}
