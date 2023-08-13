package com.example.uniappspringboot.Domain;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;


@Data
@TableName(value = "user",autoResultMap = true)
@ApiModel("用户字典")
public class User implements Serializable {//用户信息
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id ;
    @ApiModelProperty("openid")
    private String openid ;//openid
    @ApiModelProperty("自动登录的token")
    private String token;//自动登录的token
    @ApiModelProperty("地址")
    private String address;//地址
    @ApiModelProperty("详细地址")
    private String detailedaddress;//详细地址
    @ApiModelProperty("用户昵称")
    private String username ;//用户昵称
    @ApiModelProperty("用户性别")
    private String sex ;//用户性别
    @ApiModelProperty("用户密码")
    private String password ;//用户密码
    @ApiModelProperty("手机号")
    private String telephon;//手机号
    @ApiModelProperty("头像")
    private String headimage;//头像
    @ApiModelProperty("QQ邮箱")
    private String qqmailbox;//QQ邮箱
    @ApiModelProperty("备注")
    private String remarks;//备注
    @ApiModelProperty("简介")
    private String lntroduction;//简介
    @ApiModelProperty("是否显示开发人员")
    private String purview;//是否显示开发人员
    @ApiModelProperty("权限")
    private String permissions;//权限
    @ApiModelProperty("地址信息")
    @TableField(typeHandler = FastjsonTypeHandler.class)
    public JSONObject coordinate;

}
