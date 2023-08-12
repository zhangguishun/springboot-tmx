package com.example.uniappspringboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.ShoppingDao;
import com.example.uniappspringboot.Dao.UserDao;
import com.example.uniappspringboot.Domain.Shopping;
import com.example.uniappspringboot.Domain.User;
import com.example.uniappspringboot.Service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * 商品接口
 */


@Service
public class ShoppingServiceImpl implements ShoppingService {

    @Autowired
    private ShoppingDao shoppingDao;
    @Autowired
    private UserDao userDao;

    @Override//添加商品
    public R addShoppingDao(Shopping shopping){
        R r = new R();
        LambdaQueryWrapper<User> lwq=new LambdaQueryWrapper<User>();
        lwq.eq(User::getOpenid,shopping.getMerchantid());
        User userinfo=userDao.selectOne(lwq);
        SimpleDateFormat dataT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat oderShop=new SimpleDateFormat("yyyyMMddHHmmss");
        try {
            if (userinfo!=null){
                shopping.setCreatetime(String.valueOf(dataT.format(new Date())));//创建时间
                shopping.setUpdatetime(String.valueOf(dataT.format(new Date())));//更新时间
                shopping.setMerchantname(userinfo.getUsername());//商家昵称
                shopping.setMerchantphone(userinfo.getTelephon());//商家电话
                shopping.setProductid(String.valueOf(oderShop.format(new Date())));//商品编号
                try {
                    int res=shoppingDao.insert(shopping);
                    if (res==1){
                        r.setCode(String.valueOf(200));
                        r.setData(shopping);
                        r.setMsg("新增成功");
                    }else {
                        r.setCode(String.valueOf(203));
                        r.setMsg("新增失败");
                    }
                    return r;
                }catch (Exception e) {
                    r.setCode(String.valueOf(500));
                    r.setMsg(String.valueOf(e));
                    return r;
                }

            }else {
                r.setCode(String.valueOf(203));
                r.setMsg("信息错误");
            }
            return r;
        }catch (Exception i){
            r.setCode(String.valueOf(500));
            r.setMsg(String.valueOf(i));
            return r;
        }



    }

    @Override //删除商品
    public R delShoppingDao(Shopping shopping){
        R r=new R();
        try {
            int res=shoppingDao.deleteById(shopping.getId());
            if (res==1){
                r.setCode(String.valueOf(200));
                r.setMsg("s删除成功");
                return r;
            }else {
                r.setCode(String.valueOf(203));
                r.setMsg("删除失败");
                return r;
            }
        }catch (Exception e){
            r.setCode(String.valueOf(500));
            r.setMsg(String.valueOf(e));
            return r;
        }
    }

    @Override //修改商品
    public R setShoppingDao(Shopping shopping){
        R r=new R();
        try {
            int res= shoppingDao.updateById(shopping);
            if (res==1){
                r.setCode(String.valueOf(200));
                r.setMsg("修改成功");
                return r;
            }else {
                r.setCode(String.valueOf(203));
                r.setMsg("修改失败");
                return r;
            }
        }catch (Exception e){
            r.setCode(String.valueOf(500));
            r.setMsg(String.valueOf(e));
            return r;
        }
    }

    @Override //查询商品 全部
    public R selShoppingDao(Shopping shopping){
        R r=new R();
        HashMap<Object,Object> map =new HashMap();//定义Hashmap
        //总条数
        Integer listCount= Math.toIntExact(shoppingDao.selectCount(null));
        IPage page=new Page(shopping.getPage() ,shopping.getLimit());//创建分页
        map.put("count",listCount);
        //ge 大于等于
        //lt 小于
        //gt 大于
        //le 小于等于
        try {
            //搜索查询
            if(!shopping.getDataTime().get("start").equals("false")){
                QueryWrapper<Shopping> Lqw =new QueryWrapper<>();
                Lqw.ge("createtime",shopping.getDataTime().get("start"));
                Lqw.lt("createtime",shopping.getDataTime().get("end"));
                List<Shopping> res=shoppingDao.selectPage(page,Lqw).getRecords();
                map.put("list",res);
                r.setMsg("查询成功");
                r.setData(map);
                r.setCode(String.valueOf(200));
            }else
            //日期条件查询
            if(shopping.getIslike()!=null){

            }
            //全部查询
            else {

            }
            return r;
        }catch (Exception e){
            r.setCode(String.valueOf(500));
            r.setMsg(String.valueOf(e));
            return r;
        }
    }


}
