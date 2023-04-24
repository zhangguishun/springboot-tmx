package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("code")
@ApiModel("验证码字典")
public class YzmUserCode implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id ;
    @ApiModelProperty("验证码（必填）")
    private String code;
    @ApiModelProperty("时间")
    private String addtime;

}