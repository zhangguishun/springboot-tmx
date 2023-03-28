package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("shoporder")
public class Order implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String openid;
    private String merchantid;//商家id
    private String type;//商品类型
    private String tradename;//商品名称
    private String price;//下单价格
    private String description;//描述
    private String time;//下单实践
    private String amount;//数量
    private String picture;//商品图片
    private String label;//订单标签
    private String merchantname;//商家昵称
    private String status ;//订单状态

}
