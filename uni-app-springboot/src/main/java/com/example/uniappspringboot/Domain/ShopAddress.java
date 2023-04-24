package com.example.uniappspringboot.Domain;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("shoppingaddress")
@ApiModel("收货地址字典")
public class ShopAddress implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("用户openid")
    private String openid;
    @ApiModelProperty("收货人")
    private String consignee;//收货人
    @ApiModelProperty("手机号")
    private String cellphonenumber;//手机号
    @ApiModelProperty("省")
    private String province;//省
    @ApiModelProperty("市")
    private String city;//市
    @ApiModelProperty("区 州")
    private String area;//区 州
    @ApiModelProperty("详细地址")
    private String detailedaddress;//详细地址
    @ApiModelProperty("是否默认选中")
    private String checked;
}

