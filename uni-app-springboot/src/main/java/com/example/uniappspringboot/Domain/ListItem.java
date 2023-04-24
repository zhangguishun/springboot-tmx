package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@TableName("listitem")
@ApiModel("官网数据字典")
public class ListItem {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Integer id;
    @ApiModelProperty("编号")
    private Integer cateid;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("图片地址")
    private String image;
    @ApiModelProperty("访问地址")
    private String url;
}
