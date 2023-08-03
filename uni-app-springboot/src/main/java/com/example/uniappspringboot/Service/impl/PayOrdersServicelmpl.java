package com.example.uniappspringboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
            int res=payOrdersDao.deleteById(payOrders.getId());
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
    @Override //查询用户所有的订单(查询用户自己所有的)
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
    @Override //查询所有订单
    public R selAdminOdersDao(PayOrders payOrders){
        R r=new R();
        HashMap<Object,Object> map =new HashMap();//定义Hashmap
        //总条数
        Integer listCount= Math.toIntExact(payOrdersDao.selectCount(null));
        IPage page=new Page(payOrders.getPage() ,payOrders.getLimit());//创建分页
        QueryWrapper<PayOrders> Lqw =new QueryWrapper<>();
        //分页查询和模糊查询

        if (!payOrders.getDataTime().get("start").equals("false")){
            Lqw.ge("createtime",payOrders.getDataTime().get("start"));
            Lqw.lt("paytime",payOrders.getDataTime().get("end"));
            //ge 大于等于
            //lt 小于
            //gt 大于
            //le 小于等于
            List<PayOrders> res= payOrdersDao.selectPage(page,Lqw).getRecords();
            for (PayOrders re : res) {
                LambdaQueryWrapper<User> userInfo=new LambdaQueryWrapper<>();
                userInfo.eq(User::getOpenid,re.getOpenid());
                User user=userDao.selectOne(userInfo);
                HashMap<Object,Object> userMap=new HashMap<>();
                if(user!=null){
                    userMap.put("headimage",user.getHeadimage());
                    userMap.put("username",user.getUsername());
                    userMap.put("telephon",user.getTelephon());
                    re.setUserInfo(userMap);
                }

            }
            map.put("list",res);
        }else

            //搜索查询

            if(payOrders.getIslike()!=null) {
            Lqw.like("productname",payOrders.getIslike());
            List<PayOrders> res= payOrdersDao.selectPage(page,Lqw).getRecords();
            if (res.isEmpty()){
                QueryWrapper<PayOrders> Lqw1 =new QueryWrapper<>();
                Lqw1.like("productid",payOrders.getIslike());
                List<PayOrders> res1= payOrdersDao.selectPage(page,Lqw1).getRecords();
                for (PayOrders re : res1) {
                    LambdaQueryWrapper<User> userInfo=new LambdaQueryWrapper<>();
                    userInfo.eq(User::getOpenid,re.getOpenid());
                    User user=userDao.selectOne(userInfo);
                    HashMap<Object,Object> userMap=new HashMap<>();
                    if(user!=null){
                        userMap.put("headimage",user.getHeadimage());
                        userMap.put("username",user.getUsername());
                        userMap.put("telephon",user.getTelephon());
                        re.setUserInfo(userMap);
                    }
                }
                map.put("list",res1);
            }else {
                for (PayOrders re : res) {
                    LambdaQueryWrapper<User> userInfo=new LambdaQueryWrapper<>();
                    userInfo.eq(User::getOpenid,re.getOpenid());
                    User user=userDao.selectOne(userInfo);
                    HashMap<Object,Object> userMap=new HashMap<>();
                    if(user!=null){
                        userMap.put("headimage",user.getHeadimage());
                        userMap.put("username",user.getUsername());
                        userMap.put("telephon",user.getTelephon());
                        re.setUserInfo(userMap);
                    }
                }
                map.put("list",res);
            }
        }else {


                //支付状态查询
            if (payOrders.getPaystate()!=null){
                Lqw.eq("paystate",payOrders.getPaystate());
                List<PayOrders> res= payOrdersDao.selectPage(page,Lqw).getRecords();
                for (PayOrders re : res) {
                    LambdaQueryWrapper<User> userInfo=new LambdaQueryWrapper<>();
                    userInfo.eq(User::getOpenid,re.getOpenid());
                    User user=userDao.selectOne(userInfo);
                    HashMap<Object,Object> userMap=new HashMap<>();
                    if(user!=null){
                        userMap.put("headimage",user.getHeadimage());
                        userMap.put("username",user.getUsername());
                        userMap.put("telephon",user.getTelephon());
                        re.setUserInfo(userMap);
                    }
                }
                map.put("list",res);
            }else {



                //全部查询
                List<PayOrders> res= payOrdersDao.selectPage(page,null).getRecords();
                 for (PayOrders re : res) {
                     LambdaQueryWrapper<User> userInfo=new LambdaQueryWrapper<>();
                     userInfo.eq(User::getOpenid,re.getOpenid());
                     User user=userDao.selectOne(userInfo);
                     HashMap<Object,Object> userMap=new HashMap<>();
                     if(user!=null){
                         userMap.put("headimage",user.getHeadimage());
                         userMap.put("username",user.getUsername());
                         userMap.put("telephon",user.getTelephon());
                         re.setUserInfo(userMap);
                     }

                 }

                map.put("list",res);

            }
        }
        map.put("count",listCount);
        r.setMsg("查询成功");
        r.setCode(String.valueOf(200));
        r.setData(map);
        return r;
    }
}
