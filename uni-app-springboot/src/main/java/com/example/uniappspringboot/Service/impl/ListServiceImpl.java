package com.example.uniappspringboot.Service.impl;


import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.ListCateDao;
import com.example.uniappspringboot.Dao.ListItemDao;
import com.example.uniappspringboot.Domain.ListCate;
import com.example.uniappspringboot.Service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


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

}
