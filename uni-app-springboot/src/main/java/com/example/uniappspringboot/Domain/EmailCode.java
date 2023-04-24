package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("emailtab")
@ApiModel("邮箱字典")
public class EmailCode implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("邮箱验证码")
    private String code;//邮箱验证码
    @ApiModelProperty("用户id")
    private String openid;//用户openid
}
