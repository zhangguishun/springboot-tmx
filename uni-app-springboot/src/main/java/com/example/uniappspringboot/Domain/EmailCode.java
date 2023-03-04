package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
@Data
@TableName("emailtab")
public class EmailCode implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String code;//邮箱验证码
    private String openid;//用户id
}
