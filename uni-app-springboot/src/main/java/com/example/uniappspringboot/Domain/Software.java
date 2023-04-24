package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("software")
@ApiModel("软件市场模块")
public class Software {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("标题")
    private String title;//标题
    @ApiModelProperty("软件id")
    private String softid;//软件id
    @ApiModelProperty("下载地址")
    private String address;//下载地址
    @ApiModelProperty("软件内容")
    private String content;//软件内容
    @ApiModelProperty("展览图")
    private String picture;//展览图
    @ApiModelProperty("是否是会员")
    private String membership;//是否是会员
    @ApiModelProperty("售卖价格")
    private Double price;//售卖价格
    @ApiModelProperty("支付类型")
    private String paytype;//支付类型
}
