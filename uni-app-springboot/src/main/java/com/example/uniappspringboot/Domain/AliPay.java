package com.example.uniappspringboot.Domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("支付字典")
public class AliPay {
    @ApiModelProperty("订单名称")
    private String traceNo;
    @ApiModelProperty("支付总价格")
    private double totalAmount;
    @ApiModelProperty("订单信息")
    private String subject;
    @ApiModelProperty("支付订单")
    private String alipayTraceNo;
}
