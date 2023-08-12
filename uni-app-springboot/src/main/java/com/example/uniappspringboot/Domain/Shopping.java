package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

@Data
@TableName("shopping")
public class Shopping {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("商品名称（必填）")
    private String productname;//商品名称（必填）
    @ApiModelProperty("商品编号")
    private String productid;//商品编号
    @ApiModelProperty("商品描述")
    private String productdescription;//商品描述
    @ApiModelProperty("商品价格（必填）")
    private BigDecimal productprice;//商品价格（必填）
    @ApiModelProperty("商品库存（必填）")
    private String productstock;//商品库存（必填）
    @ApiModelProperty("创建时间")
    private String createtime;//创建时间
    @ApiModelProperty("更新时间")
    private String updatetime;//更新时间
    @ApiModelProperty("商户昵称（必填）")
    private String merchantname;//商户昵称（必填）
    @ApiModelProperty("商户id（必填）")
    private String merchantid;//商户id（必填）
    @ApiModelProperty("商家电话（必填）")
    private String merchantphone;//商家电话（必填）
    @ApiModelProperty("营业状态")
    private String businessstatus;//营业状态
    @ApiModelProperty("商品图片")
    private String productimage;//商品图片
    @ApiModelProperty("商品标签")
    private String productlabel;//商品标签
    @ApiModelProperty("商品品类")
    private String category;//商品品类
    @ApiModelProperty("优惠 打折 默认：10 7折就是7")
    private String preferential;//优惠 打折
    @ApiModelProperty("已销售")
    private String sold;//已销售
    @ApiModelProperty("分类")
    private String maintype;//分类
    @ApiModelProperty("支付类型")
    private String paytype;//支付类型
    @ApiModelProperty("查询限制条数")
    @TableField(exist = false)
    private Integer limit;
    @ApiModelProperty("查询分页页数")
    @TableField(exist = false)
    private Integer page;
    @ApiModelProperty("查询时间段")
    @TableField(exist = false)
    private HashMap<Object,Object> dataTime;
    @ApiModelProperty("查询搜索内容")
    @TableField(exist = false)
    private String islike;
}