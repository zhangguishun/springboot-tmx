package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限
 */

@Data
@TableName("privileges")
@ApiModel("权限字典")
public class Privileges implements Serializable {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("权限名称（必填）")
    private String name;//权限名字
    @ApiModelProperty("权限字段（必填）")
    private String permission;//权限字段
    @ApiModelProperty("备注")
    private String remarks;//备注
    @ApiModelProperty("选中状态（PC）")
    private String slected;//选择

}
