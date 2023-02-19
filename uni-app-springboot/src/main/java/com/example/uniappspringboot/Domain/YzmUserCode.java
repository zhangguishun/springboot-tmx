package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("code")
public class YzmUserCode implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id ;
    private String code;
    private String addtime;

}