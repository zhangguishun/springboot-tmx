package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("listitem")
public class ListItem {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer cateid;
    private String name;
    private String image;
    private String url;
}
