package com.example.uniappspringboot.Domain;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("shoppingaddress")
public class ShopAddress implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id ;
    private String openid;
    private JSON shippingaddress;

}
