package com.example.uniappspringboot.Domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 权限
 */

@Data
@TableName("privileges")
public class Privileges implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;//权限名字
    private String permission;//权限字段
    private String remarks;//备注

}
