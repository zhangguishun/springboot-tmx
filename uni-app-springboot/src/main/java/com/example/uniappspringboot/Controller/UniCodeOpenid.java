package com.example.uniappspringboot.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.uniappspringboot.Config.R;
import com.example.uniappspringboot.Domain.Unicode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@CrossOrigin
@RestController
@RequestMapping(value = "unicode")
public class UniCodeOpenid {
    @PostMapping("/GetOpenid")
    public R OpenidCode (@RequestBody Unicode unicode) throws IOException {
        String AppId="wxd3dc9798722c1c6b";
        String Secret="b982ad390daca25f8cf7631df639d8bd";
        String url="https://api.weixin.qq.com/sns/jscode2session?appid="+AppId+"&secret="+Secret+"&js_code="+unicode.getCode()+"&grant_type=authorization_code ";
        OkHttpClient client =new OkHttpClient();
        Request request =new Request.Builder().url(url).build();
        Response response =client.newCall(request).execute();
        R r =new R();
        if(response.isSuccessful()){
            String body =response.body().string();
            JSONObject jsonObj = JSON.parseObject(body);
            r.setData(jsonObj);
            r.setCode(String.valueOf(200));
        }
        return  r;
    }

}
