package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("listcate")
@ApiModel("弃用")
public class ListCate {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("昵称")
    private String name;
}
