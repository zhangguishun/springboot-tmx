package com.example.uniappspringboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Config.Time;
import com.example.uniappspringboot.Dao.PrivilegesDao;
import com.example.uniappspringboot.Dao.UserDao;
import com.example.uniappspringboot.Domain.User;
import com.example.uniappspringboot.Service.UserService;
import com.example.uniappspringboot.Util.OpenidUtil;
import com.example.uniappspringboot.Util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PrivilegesDao privilegesDao;

    @Override //注册
    public R setUser(User user){
        R r=new R();
        String token= TokenUtils.sign(user);
       // String token="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImlkIjoyLCJleHAiOjE2Nzg4MTkzNTN9.LCMtIb59Du1gqqDw0NGjf8MDJqsESCfOQkXsTE1V1LM";
        //boolean res= TokenUtils.verify(token);
        r.setData(token);
        r.setMsg("成功");
        r.setCode(String.valueOf(200));
        return r;
    }

    @Override //用户注册信息
    public R Registration(User user) {
        LambdaQueryWrapper<User> lqw =new LambdaQueryWrapper<User>();
        lqw.eq(User::getTelephon,user.getTelephon());
        User GetInfo = userDao.selectOne(lqw);
        Date date = new Date();//获取当前标准时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//装换格式

        R r = new R();
        if(GetInfo==null){
            String openid=OpenidUtil.generateToken(user);//获取openid
            user.setOpenid(openid);//插入openid
            user.setToken(sdf.format(date));//自定义登录失效时间
            int list= userDao.insert(user);//插入数据
            if (list==1){
                LambdaQueryWrapper<User> Lqw=new LambdaQueryWrapper<>();
                Lqw.eq(User::getOpenid,openid);
                User newUser =userDao.selectOne(Lqw);//查询刚注册的数据
                String token= TokenUtils.sign(newUser);//获取请求头token
                ArrayList data=new ArrayList();//建立一个数组
                  data.add(openid);
                  data.add(token);
                r.setData(data);//返回数据
                r.setMsg("注册成功");
                r.setCode(String.valueOf(200));
            }
        }else {
            r.setData(204);
            r.setMsg("该手机号已经存在");
            r.setCode(String.valueOf(204));
        }
        return r;
    }

    @Override //登录
    public R PLogin(User user){
        LambdaQueryWrapper<User> lqw =new LambdaQueryWrapper<User>();
        lqw.eq(User::getTelephon,user.getTelephon()).eq(User::getPassword,user.getPassword());
        User  list =userDao.selectOne(lqw);
        R r = new R();
        if(list==null){
            r.setData("");
            r.setMsg("登录失败");
            r.setCode(String.valueOf(203));
        }else if (list.getPassword().equals(user.getPassword())){
            Date date = new Date();//获取当前标准时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//装换格式
            list.setToken(sdf.format(date));
            userDao.updateById(list);
            ArrayList arrayList=new ArrayList();
             arrayList.add(list.getOpenid());
             String token= TokenUtils.sign(list);
             arrayList.add(token);
             arrayList.add(list.getPermissions());
            r.setData(arrayList);
            r.setMsg("登录成功");
            r.setCode(String.valueOf(200));
        }else {
            r.setData("");
            r.setMsg("登录失败");
            r.setCode(String.valueOf(203));
        }
        return r;
    }

    @Override //校验token是否过期（时间）
    public R CheckToken(User user){
        LambdaQueryWrapper<User> lqw =new LambdaQueryWrapper<User>();
        lqw.eq(User::getOpenid,user.getOpenid());
        User  list =userDao.selectOne(lqw);
        R r = new R();
        if(list==null){
            r.setMsg("校验失败");
            r.setCode(String.valueOf(203));
        }else if(list!=null) {
            if(list.getOpenid().equals(user.getOpenid())){
                Date date = new Date();//获取当前标准时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//装换格式
                int difference= (int) Time.getDistanceTimes(list.getToken(), sdf.format(date));
                if(difference<=120){
                    list.setToken(sdf.format(date));
                    userDao.updateById(list);
                    r.setMsg("校验通过");
                    r.setCode(String.valueOf(200));
                }else {
                    r.setMsg("需要重新登录");
                    r.setCode(String.valueOf(201));
                }
            }else {
                r.setMsg("校验失败");
                r.setCode(String.valueOf(203));
            }
        }

        return r;
    }

    @Override //查询单个用户信息
    public R SelectUserIofo(User user){
        LambdaQueryWrapper<User> lqw =new LambdaQueryWrapper<User>();
        lqw.eq(User::getOpenid,user.getOpenid());

        R r=new R();
        try {
            User UserInfo =userDao.selectOne(lqw);
            UserInfo.setPassword("");
            r.setData(UserInfo);
            r.setCode(String.valueOf(200));
            r.setMsg("查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            r.setData(e);
            r.setCode(String.valueOf(500));
            r.setMsg("查询失败");
        }
        return r;
    }

    @Override //查询所有信息（全部）
    public R getlogin(){
        List<User> users = userDao.selectList(null);
        users.forEach((item)->{item.setPassword("");});
        R r = new R();
        r.setData(users);
        r.setCode(String.valueOf(200));
        r.setMsg("查询成功");
        return r;
    }

    @Override //头像上传
    public R Avatar(User user){
        R r =new R();
        LambdaQueryWrapper<User> lqw =new LambdaQueryWrapper<User>();
        lqw.eq(User::getOpenid,user.getOpenid());
        try {
            User UserInfo =userDao.selectOne(lqw);
            if(UserInfo==null){
                r.setMsg("信息异常");
                r.setCode(String.valueOf(404));
            }else {
                UserInfo.setHeadimage(user.getHeadimage());
              int res= userDao.updateById(UserInfo);
                r.setCode(String.valueOf(200));
                r.setMsg("上传成功");
                r.setData(UserInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            r.setCode(String.valueOf(500));
            r.setData(e);
        }

        return  r;
    }

    @Override //用户地址  和详细地址
    public R Detailed_address(User user){
        R r =new R();
        LambdaQueryWrapper<User> lqw =new LambdaQueryWrapper<User>();
        lqw.eq(User::getOpenid,user.getOpenid());
        try {
            User UserInfo =userDao.selectOne(lqw);
            if(UserInfo==null){
                r.setMsg("信息异常");
                r.setCode(String.valueOf(404));
            }else {
                UserInfo.setAddress(user.getAddress());
                UserInfo.setDetailedaddress(user.getDetailedaddress());
                int res= userDao.updateById(UserInfo);
                r.setCode(String.valueOf(200));
                r.setMsg("修改成功");
                r.setData(UserInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
            r.setCode(String.valueOf(500));
            r.setData(e);
        }
        return r;
    }

    @Override //修改用户信息
    public R setUserInfo(User user) throws IOException {
        LambdaQueryWrapper<User> lqw =new LambdaQueryWrapper<User>();
        lqw.eq(User::getOpenid,user.getOpenid());
        User Info =userDao.selectOne(lqw);
        user.setId(Info.getId());
        R r =new R();
        int res=userDao.updateById(user);
        if(res==1){
            r.setMsg("修改成功");
            r.setCode(String.valueOf(200));
        }else {
            r.setMsg("修改失败");
            r.setCode(String.valueOf(203));
        }
        return r;
    }

    @Override//分页查询
    public R setUserPages(User user){
        R r=new R();
        int pageNum= Integer.parseInt(user.getRemarks());
        int pageNums= Integer.parseInt((user.getLntroduction()));
        IPage page=new Page(pageNum ,pageNums);//创建分页
       //备注：需要使用拦截器（MpConfig）
        List<User> res= userDao.selectPage(page,null).getRecords();
       Long res1=userDao.selectCount(null);
       ArrayList result=new ArrayList();
        result.add(res);
        result.add(res1);
        res.forEach(item->{item.setPassword(null);});
        if (res.isEmpty()){
            r.setMsg("查询失败");
            r.setCode(String.valueOf(203));
        }

        r.setMsg("成功");
        r.setData(result);
        r.setCode(String.valueOf(200));
        return r;
    }

    @Override //综合网关于我们
    public R selPurview(User user){
        LambdaQueryWrapper<User> lqw =new LambdaQueryWrapper<User>();
        lqw.eq(User::getPurview,user.getPurview());
        ArrayList res= (ArrayList) userDao.selectList(lqw);
        ArrayList result =new ArrayList();
        res.forEach((item)->{
            HashMap<String,String> map=new ManagedMap<>();
            User value= (User) item;
            map.put("avatar",value.getHeadimage());
            map.put("remarks",value.getRemarks());
            map.put("username",value.getUsername());
            map.put("Introduction",value.getLntroduction());
            result.add(map);
        });
        R r =new R();
        if(res.isEmpty()){
            r.setMsg("查询失败");
            r.setCode(String.valueOf(203));

        }else {
            r.setMsg("查询成功");
            r.setCode(String.valueOf(200));
            r.setData(result);
        }
        return r;
    }

    @Override //查询所有用户坐标
    public R selCoordinate(){
        R r = new R();
        QueryWrapper<User> queryWrapper =new QueryWrapper<>();
        queryWrapper.select("coordinate","openid","username");
        ArrayList users = (ArrayList) userDao.selectList(queryWrapper);
        r.setCode(String.valueOf(200));
        r.setMsg("查询成功");
        r.setData(users);
        return r;
    }

}
