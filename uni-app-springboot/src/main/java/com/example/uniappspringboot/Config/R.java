package com.example.uniappspringboot.Config;

import lombok.Data;

@Data
public class R {

    private String code; //1:成功 0：失败

    private String msg;  //返回的消息

    private Object data; //返回的数据；

}
