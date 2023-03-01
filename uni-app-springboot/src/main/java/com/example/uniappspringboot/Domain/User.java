package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user")
public class User implements Serializable {//用户信息
    @TableId(type = IdType.AUTO)
    private Long id ;
    private String openid ;//openid
    private String token;//自动登录的token
    private String address;//地址
    private String detailed_address;//详细地址
    private String username ;//用户昵称
    private String password ;//用户密码
    private String telephon;//手机号
    private String headimage;//头像
    private String qq_mailbox;//QQ邮箱
}
