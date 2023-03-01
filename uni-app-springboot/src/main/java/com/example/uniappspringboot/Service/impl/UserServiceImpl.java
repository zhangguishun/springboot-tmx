package com.example.uniappspringboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Config.Time;
import com.example.uniappspringboot.Dao.UserDao;
import com.example.uniappspringboot.Domain.User;
import com.example.uniappspringboot.Service.UserService;
import com.example.uniappspringboot.Util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override //用户注册信息
    public R Registration(User user) {
        LambdaQueryWrapper<User> lqw =new LambdaQueryWrapper<User>();
        lqw.eq(User::getTelephon,user.getTelephon());
        User GetInfo = userDao.selectOne(lqw);
        Date date = new Date();//获取当前标准时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//装换格式
        R r = new R();
        if(GetInfo==null){
            user.setOpenid(TokenUtil.generateToken(user));
            user.setToken(sdf.format(date));
                userDao.insert(user);
                r.setData(TokenUtil.generateToken(user));
                r.setMsg("注册成功");
                r.setCode(String.valueOf(200));
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
            r.setData(list.getOpenid());
            r.setMsg("登录成功");
            r.setCode(String.valueOf(200));
        }else {
            r.setData("");
            r.setMsg("登录失败");
            r.setCode(String.valueOf(203));
        }
        return r;
    }

    @Override //校验token是否过期
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

    @Override //查询所有信息
    public R getlogin(User user){
        List<User> users = userDao.selectList(null);
        R r = new R();
        r.setData(users);
        r.setCode(String.valueOf(1));
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
                UserInfo.setDetailed_address(user.getDetailed_address());
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
    public R setUserInfo(User user){
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
}
