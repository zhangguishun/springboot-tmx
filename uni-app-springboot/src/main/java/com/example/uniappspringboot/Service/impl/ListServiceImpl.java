package com.example.uniappspringboot.Service.impl;


import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.ListCateDao;
import com.example.uniappspringboot.Dao.ListItemDao;
import com.example.uniappspringboot.Domain.ListCate;
import com.example.uniappspringboot.Domain.ListItem;
import com.example.uniappspringboot.Service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;


@Service
public class ListServiceImpl implements ListService {

    @Autowired
    private ListItemDao listItemDao;

    @Autowired
    private ListCateDao listCateDao;

    @Override //获取列表数据
    public R selList(){
        R r =new R();
        ArrayList resItem= (ArrayList) listItemDao.selectList(null);
        ArrayList resCate = (ArrayList) listCateDao.selectList(null);
        HashMap<Object,Object> map=new HashMap<>();
        map.put("name",resCate);
        map.put("list",resItem);
        if (resItem.isEmpty()){
            r.setCode(String.valueOf(203));
            r.setMsg("失败");
        }else {
            r.setCode(String.valueOf(200));
            r.setMsg("成功");
            r.setData(map);
        }
        return r;
    }

    @Override //增加数据
    public R addList(ListItem listItem){
        R r=new R();
        int res=listItemDao.insert(listItem);
        if (res==1){
            r.setCode(String.valueOf(200));
            r.setMsg("新增成功");
        }else {
            r.setCode(String.valueOf(203));
            r.setMsg("新增失败");
        }
        return r;
    }

    @Override //修改数据
    public R setList(ListItem listItem){
        R r=new R();
        int res=listItemDao.updateById(listItem);
        if (res==1){
            r.setCode(String.valueOf(200));
            r.setMsg("修改成功");
        }else {
            r.setCode(String.valueOf(203));
            r.setMsg("修改失败");
        }
        return r;
    }

    @Override //删除数据
    public R delList(ListItem listItem){
        R r=new R();
        System.out.println(listItem);
        int res=listItemDao.deleteById(listItem);
        if (res==1){
            r.setCode(String.valueOf(200));
            r.setMsg("删除成功");
        }else {
            r.setCode(String.valueOf(203));
            r.setMsg("删除失败");
        }
        return r;
    }

    @Override //新增分类
    public R addCate(ListCate listCate){
        R r=new R();
        int res=listCateDao.insert(listCate);
        if (res==1){
            r.setCode(String.valueOf(200));
            r.setMsg("新增成功");
        }else {
            r.setCode(String.valueOf(203));
            r.setMsg("新增失败");
        }
        return r;
    }

    @Override //修改分类
    public R setCate(ListCate listCate){
        R r=new R();
        int res=listCateDao.updateById(listCate);
        if (res==1){
            r.setCode(String.valueOf(200));
            r.setMsg("修改成功");
        }else {
            r.setCode(String.valueOf(203));
            r.setMsg("修改失败");
        }
        return r;
    }
}
