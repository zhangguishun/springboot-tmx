package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("payorders")
public class PayOrders {

    // 支付创建订单号
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String  openid;//用户openid(必填)
    private String  productname;//商品名称
    private String  productid;//商品id（必填）
    private String  alipayno;//支付订单
    private String  createtime;//创建时间
    private String  paytime;//支付时间
    private String  paystate;//支付状态
    private String  productprice;//支付价格
    private Integer productnum;//商品数量（必填）
    private String  merchantid;//商家id
    private String  merchantphone;//商家手机号
    private String  merchantname;//商家昵称
    private String  productimage;//商品图片
    private String  category;//商品品类
    private String  preferential;//打折
    private String  maintype;//商品分类
    private String  productdescription;//商品详细信息

}