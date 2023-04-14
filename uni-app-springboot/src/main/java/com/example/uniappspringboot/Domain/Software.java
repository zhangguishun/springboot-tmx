package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("software")
public class Software {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;//标题
    private String address;//下载地址
    private String content;//软件内容
    private String picture;//展览图
    private String membership;//是否是会员
    private Double price;//售卖价格
}
