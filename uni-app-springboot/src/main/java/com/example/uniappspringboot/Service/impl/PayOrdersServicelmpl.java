package com.example.uniappspringboot.Service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.PayOrdersDao;
import com.example.uniappspringboot.Dao.ShoppingDao;
import com.example.uniappspringboot.Dao.SoftwareDao;
import com.example.uniappspringboot.Dao.UserDao;
import com.example.uniappspringboot.Domain.PayOrders;
import com.example.uniappspringboot.Domain.Shopping;
import com.example.uniappspringboot.Domain.Software;
import com.example.uniappspringboot.Domain.User;
import com.example.uniappspringboot.Service.PayOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.example.uniappspringboot.Util.secretUtil.desEncrypt;


@Service
public class PayOrdersServicelmpl implements PayOrdersService {
    @Autowired
    private PayOrdersDao payOrdersDao;
    @Autowired
    private ShoppingDao shoppingDao;
    @Autowired
    private SoftwareDao softwareDao;
    @Autowired
    private UserDao userDao;

    @Override //新增加订单
    public R addPayOrdersDao(PayOrders payOrders){
        R r=new R();
        LambdaQueryWrapper<Shopping> lwq=new LambdaQueryWrapper<>();
        lwq.eq(Shopping::getProductid,payOrders.getProductid());
        try {
            Shopping shopInfo =shoppingDao.selectOne(lwq);
            if (shopInfo!=null){
                SimpleDateFormat dataT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间
                SimpleDateFormat oderShop=new SimpleDateFormat("yyyyMMddHHmmss");//订单
                String lipay_no=shopInfo.getProductid()+oderShop.format(new Date());//生成支付订单
                BigDecimal num=new BigDecimal(shopInfo.getPreferential());
                String resPrice= String.valueOf(shopInfo.getProductprice().multiply(num)
                        .multiply(BigDecimal.valueOf(0.1))
                        .multiply(BigDecimal.valueOf(payOrders.getProductnum())));
                payOrders.setProductname(shopInfo.getProductname());//商品名称
                payOrders.setAlipayno(lipay_no);//支付订单
                payOrders.setCreatetime(dataT.format(new Date()));
                payOrders.setPaystate("未支付");
                payOrders.setProductprice(resPrice);
                payOrders.setMerchantid(shopInfo.getMerchantid());
                payOrders.setMerchantname(shopInfo.getMerchantname());//商家
                payOrders.setMerchantphone(shopInfo.getMerchantphone());//商家手机号
                payOrders.setProductname(shopInfo.getProductname());//商品名称
                payOrders.setProductimage(shopInfo.getProductimage());//商品图片
                payOrders.setCategory(shopInfo.getCategory());//打折
                payOrders.setPreferential(shopInfo.getPreferential());//
                payOrders.setMaintype(shopInfo.getMaintype());//主类
                payOrders.setProductdescription(shopInfo.getProductdescription());//商品描述
                payOrders.setPaytype(shopInfo.getPaytype());//支付类型
            int resOrder= payOrdersDao.insert(payOrders);
            if (resOrder==1){
                r.setCode(String.valueOf(200));
                r.setMsg("创建成功");
                r.setData(payOrders);
            }else {
                r.setCode(String.valueOf(203));
                r.setMsg("订单异常");
            }

            }else {
                r.setCode(String.valueOf(203));
                r.setMsg("订单异常");
            }
            return r;
        }catch (Exception e){
            r.setCode(String.valueOf(500));
            r.setMsg("服务异常");
            r.setData(e);
            return r;
        }

    }
    @Override //删除订单
    public R delPayOdersDao(PayOrders payOrders){
        R r=new R();
        try {
            int res=shoppingDao.deleteById(payOrders.getId());
            if (res==1){
                r.setCode(String.valueOf(200));
                r.setMsg("删除成功");
            }else {
                r.setCode(String.valueOf(203));
            }
            return r;
        }catch (Exception e){
            r.setCode(String.valueOf(500));
            r.setMsg(String.valueOf(e));
            return r;
        }

    }
    @Override //更新订单（单个查询）
    public R setPayOdersDao(PayOrders payOrders){
        R r=new R();
        try {
            int res=payOrdersDao.updateById(payOrders);
            if (res==1){
                r.setCode(String.valueOf(200));
                r.setMsg("更新成功");
            }else {
                r.setCode(String.valueOf(203));
            }
            return r;
        }catch (Exception e){
            r.setCode(String.valueOf(500));
            r.setMsg(String.valueOf(e));
            return r;
        }

    }
    @Override //查询用户所有的订单()
    public  R selsPayOdersDao(PayOrders payOrders){
        R r=new R();
        LambdaQueryWrapper<PayOrders> oderLqw =new LambdaQueryWrapper<>();
        oderLqw.eq(PayOrders::getProductid,payOrders.getProductid())
                .eq(PayOrders::getOpenid,payOrders.getOpenid());

        LambdaQueryWrapper<Software> softLqw=new LambdaQueryWrapper<>();
        softLqw.eq(Software::getSoftid,payOrders.getProductid());

        LambdaQueryWrapper<User> userLqw=new LambdaQueryWrapper<User>();
        userLqw.eq(User::getOpenid,payOrders.getOpenid());

            Software soft=softwareDao.selectOne(softLqw);

                if (soft.getMembership().equals("Ordinary")){//免费下载
                  r.setMsg("验证成功");
                  r.setData(soft.getAddress());
                  r.setCode(String.valueOf(200));
                  //  System.out.printf("免费");
                  return r;
            }
                else if (soft.getMembership().equals("SellingGoods")){//单独售卖
                try {
                    PayOrders resOder=payOrdersDao.selectOne(oderLqw);
                    if ( resOder!=null&&resOder.getPaystate().equals("已支付") &&resOder.getPaytime()!=null){
                        r.setMsg("验证成功");
                        r.setData(soft.getAddress());
                        r.setCode(String.valueOf(200));
                    }else if (resOder!=null&&resOder.getPaystate().equals("未支付")){
                        r.setMsg("已有订单");
                        r.setData(resOder);
                        r.setCode(String.valueOf(2001));
                    }else {
                        r.setMsg("验证失败");
                        r.setData("请重新下单！");
                        r.setCode(String.valueOf(4041));
                        // System.out.printf("单独购买失败");
                    }
                }catch (Exception e){
                    r.setMsg(String.valueOf(e));
                }
                    return r;
            }
                else if (soft.getMembership().equals("SVIPusers")) {//会员专享
                   User resUser=userDao.selectOne(userLqw);
                   String desRes=desEncrypt(resUser.getPermissions());
                   PayOrders resOder=payOrdersDao.selectOne(oderLqw);
                    System.out.println(resOder);
                    if (desRes.contains("SVIPusers")==true){
                        r.setMsg("验证成功");
                        r.setData(soft.getAddress());
                        r.setCode(String.valueOf(200));
                    }else  if (resOder!=null&&resOder.getPaystate().equals("未支付")){
                        r.setMsg("已有订单");
                        r.setData(resOder);
                        r.setCode(String.valueOf(2001));
                    }else {
                        r.setMsg("验证失败");
                        r.setData("您暂未开通会员！");
                        r.setCode(String.valueOf(4042));
                    }
                    return r;

            }
                else {
                    r.setMsg("信息错误");
                    r.setCode(String.valueOf(203));
                    return r;
                }


    }
}
