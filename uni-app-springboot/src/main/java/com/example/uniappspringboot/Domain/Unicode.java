package com.example.uniappspringboot.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("uniopenid")
public class Unicode {
    @ApiModelProperty("uni-openid通行码")
    private String code;
}
