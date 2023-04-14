package com.example.uniappspringboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.PayOrdersDao;
import com.example.uniappspringboot.Dao.ShoppingDao;
import com.example.uniappspringboot.Domain.PayOrders;
import com.example.uniappspringboot.Domain.Shopping;
import com.example.uniappspringboot.Service.PayOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class PayOrdersServicelmpl implements PayOrdersService {
    @Autowired
    private PayOrdersDao payOrdersDao;
    @Autowired
    private ShoppingDao shoppingDao;

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
                payOrders.setMerchantname(shopInfo.getMerchantname());
                payOrders.setMerchantphone(shopInfo.getMerchantphone());
                payOrders.setProductname(shopInfo.getProductname());
                payOrders.setProductimage(shopInfo.getProductimage());
                payOrders.setCategory(shopInfo.getCategory());
                payOrders.setPreferential(shopInfo.getPreferential());
                payOrders.setMaintype(shopInfo.getMaintype());
                payOrders.setProductdescription(shopInfo.getProductdescription());
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

    public R selPayOdersDao(PayOrders payOrders){
        R r=new R();
        try {
            int res=payOrdersDao.updateById(payOrders);
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
}
