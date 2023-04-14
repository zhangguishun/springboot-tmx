package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("shopping")
public class Shopping {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String productname;//商品名称（必填）
    private String productid;//商品编号
    private String productdescription;//商品描述
    private BigDecimal productprice;//商品价格（必填）
    private String productstock;//商品库存（必填）
    private String createtime;//创建时间
    private String updatetime;//更新时间
    private String merchantname;//商户昵称（必填）
    private String merchantid;//商户id（必填）
    private String merchantphone;//商家电话（必填）
    private String businessstatus;//营业状态
    private String productimage;//商品图片
    private String productlabel;//商品标签
    private String category;//商品品类
    private String preferential;//优惠 打折
    private String sold;//已销售
    private String maintype;//分类
}