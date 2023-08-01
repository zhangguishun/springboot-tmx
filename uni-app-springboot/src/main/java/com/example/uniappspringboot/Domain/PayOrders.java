package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.spring.web.json.Json;

import java.math.BigDecimal;
import java.sql.Array;
import java.util.Date;
import java.util.HashMap;

@Data
@TableName("payorders")
@ApiModel("支付订单字典")
public class PayOrders {

    // 支付创建订单号
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("用户openid(必填)")
    private String  openid;//用户openid(必填)
    @ApiModelProperty("商品名称")
    private String  productname;//商品名称
    @ApiModelProperty("商品订单（必填）")
    private String  productid;//商品id（必填）
    @ApiModelProperty("支付订单")
    private String  alipayno;//支付订单
    @ApiModelProperty("创建时间")
    private String  createtime;//创建时间
    @ApiModelProperty("支付时间")
    private String  paytime;//支付时间
    @ApiModelProperty("支付状态")
    private String  paystate;//支付状态
    @ApiModelProperty("支付价格")
    private String  productprice;//支付价格
    @ApiModelProperty("商品数量（必填）")
    private Integer productnum;//商品数量（必填）
    @ApiModelProperty("商家openid")
    private String  merchantid;//商家id
    @ApiModelProperty("商家手机号")
    private String  merchantphone;//商家手机号
    @ApiModelProperty("商家昵称")
    private String  merchantname;//商家昵称
    @ApiModelProperty("商品图片")
    private String  productimage;//商品图片
    @ApiModelProperty("商品品类")
    private String  category;//商品品类
    @ApiModelProperty("打折(实例：默认是10，八折就是8)")
    private String  preferential;//打折
    @ApiModelProperty("商品分类")
    private String  maintype;//商品分类
    @ApiModelProperty("商品详细信息")
    private String  productdescription;//商品详细信息
    @ApiModelProperty("支付类型")
    private String  paytype;//支付类型
    //形参参数
    @ApiModelProperty("分页限制条数")
    @TableField(exist = false)
    private Integer limit;
    @ApiModelProperty("分页页数")
    @TableField(exist = false)
    private Integer page;
    @ApiModelProperty("模糊查询内容")
    @TableField(exist = false)
    private String islike;
    @TableField(exist = false)
    private HashMap<Object,Object> dataTime;
}