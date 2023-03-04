package com.example.uniappspringboot.Domain;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("shoppingaddress")
public class ShopAddress implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String openid;
    private String consignee;//收货人
    private String cellphonenumber;//手机号
    private String province;//省
    private String city;//市
    private String area;//区 州
    private String detailedaddress;//详细地址
    private String checked;
}

