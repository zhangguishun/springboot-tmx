package com.example.uniappspringboot.Service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Dao.YzmCodeDao;
import com.example.uniappspringboot.Domain.YzmUserCode;
import com.example.uniappspringboot.Service.YzmCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class YzmCodeServicelmpI implements YzmCodeService {
    @Autowired
    private YzmCodeDao yzmCodeDao;

    @Override //验证码获取和判断
    public R getYzmCode(YzmUserCode yzmUserCode){
        LambdaQueryWrapper<YzmUserCode> lqw =new LambdaQueryWrapper<YzmUserCode>();
        lqw.eq(YzmUserCode::getCode,yzmUserCode.getCode());
        YzmUserCode GetCodeInfo = yzmCodeDao.selectOne(lqw);
        R r=new R();
        if(GetCodeInfo==null){
            r.setCode("203");
            r.setMsg("验证码错误");
            r.setData(0);
        }else {
            if(GetCodeInfo.getCode().equals(yzmUserCode.getCode())){
                r.setCode("200");
                r.setMsg("验证成功");
                r.setData("");
            }else  {
                r.setCode("203");
                r.setMsg("验证码错误");
                r.setData(0);
            }
        }

        return r;
    }

    @Override //验证码存入
    public R postYzmCode(String yzmCode){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdf.format(date);
        YzmUserCode list =new YzmUserCode();
        list.setCode(yzmCode);
        list.setAddtime(String.valueOf(dateStr));
        int tf = yzmCodeDao.insert(list);
        R r=new R();
        if (tf==1){
            r.setCode("200");
            r.setMsg("成功");
            r.setData(yzmCode);
        }else {
            r.setCode("203");
            r.setMsg("失败");
            r.setData(0);
        }
        return r;
    }

    @Override //清除所有code
    public R DeletCode(){
        int delcode = yzmCodeDao.delete(null);
        R r=new R();
        r.setCode(String.valueOf(200));
        r.setMsg("成功删除"+delcode+"条");
        r.setData(delcode);
        return r;
    }
}