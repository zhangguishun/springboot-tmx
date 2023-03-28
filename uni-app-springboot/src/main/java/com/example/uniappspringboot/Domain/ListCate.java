package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("listcate")
public class ListCate {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
}
