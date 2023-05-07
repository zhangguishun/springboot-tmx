package com.example.uniappspringboot.Service;


import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.ListCate;
import com.example.uniappspringboot.Domain.ListItem;

public interface ListService {
    //获取列表数据(分类和数据项)
    R selList();

    //修改数据
    R setList(ListItem listItem);

    //增加数据
    R addList(ListItem listItem);

    //删除数据
    R delList(ListItem listItem);

    //新增分类
    R addCate(ListCate listCate);

    //修改分类
    R setCate(ListCate listCate);
}
